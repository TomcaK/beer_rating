package cz.tomaskopulety.beer_rating.service;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.database.RatingRepository;
import cz.tomaskopulety.beer_rating.database.module.RatingEntity;
import cz.tomaskopulety.beer_rating.service.domain.Rating;
import cz.tomaskopulety.beer_rating.service.domain.RatingUpdate;
import cz.tomaskopulety.beer_rating.service.mapper.DomainMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RatingService {

    @Nonnull
    private final RatingRepository ratingRepository;

    @Nonnull
    private final BeerService beerService;

    @Nonnull
    private final DomainMapper mapper;

    @Nonnull
    private final ReentrantReadWriteLock lock;

    @Nonnull
    public Rating createRating(@Nonnull Rating rating) {
        try {
            this.lock.writeLock().lock();
            if (!this.beerService.existBeer(rating.getBeerId())) {
                throw new IllegalArgumentException(String.format("Beer id: %s not found.", rating.getBeerId()));
            }
            return this.mapper.map(this.ratingRepository.save(this.mapper.map(rating)));
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Nonnull
    public List<Rating> getRatings() {
        try {
            this.lock.readLock().lock();
            return this.ratingRepository.findAll()
                    .stream()
                    .map(this.mapper::map)
                    .toList();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Nonnull
    public Rating updateRating(@Nonnull RatingUpdate rating) {
        final RatingEntity ratingEntity = this.ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Rating id %s not found.", rating.getId())));

        final String note = switch (rating.getNote()){
            case null -> ratingEntity.getNote();
            case "" -> null;
            default -> rating.getNote();
        };

        ratingEntity.setValue(rating.getValue() == null ? ratingEntity.getValue() : rating.getValue());
        ratingEntity.setNote(note);

        try {
            this.lock.writeLock().lock();
            return this.mapper.map(this.ratingRepository.save(ratingEntity));
        } finally {
            this.lock.writeLock().unlock();
        }
    }

}
