package cz.tomaskopulety.beer_rating.service;

import java.util.List;

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
    public Rating createRating(@Nonnull Rating rating) {
        if (!this.beerService.existBeer(rating.getBeerId())) {
            throw new IllegalArgumentException(String.format("Beer id: %s not found.", rating.getBeerId()));
        }
        final RatingEntity ratingEntity = this.ratingRepository.save(this.mapper.map(rating));
        return this.mapper.map(ratingEntity);
    }

    @Nonnull
    public List<Rating> getRatings() {
        return this.ratingRepository.findAll()
                .stream()
                .map(this.mapper::map)
                .toList();
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

        return this.mapper.map(this.ratingRepository.save(ratingEntity));
    }

}
