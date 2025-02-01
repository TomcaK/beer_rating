package cz.tomaskopulety.beer_rating.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.service.domain.Beer;
import cz.tomaskopulety.beer_rating.service.domain.Rating;
import cz.tomaskopulety.beer_rating.service.mapper.DomainMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class StatisticsService {

    @Nonnull
    private final BeerService beerService;

    @Nonnull final RatingService ratingService;

    @Nonnull
    private final DomainMapper mapper;

    @Nonnull
    public List<Beer> getRatedBeers() {
        final List<Beer> beers = this.beerService.getBeers();
        final List<Rating> ratings = this.ratingService.getRatings();
        final List<Beer> modifiedBeers = new ArrayList<>(beers.size());
        for (final Beer beer : beers) {
            final List<Rating> beerRatings = ratings.stream()
                    .filter(r -> r.getBeerId() == beer.getId())
                    .toList();

            if (beerRatings.isEmpty()) {
                modifiedBeers.add(beer);
            } else {
                modifiedBeers.add(this.mapper.map(beer, ratings));
            }
        }


        return modifiedBeers;
    }

}
