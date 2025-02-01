package cz.tomaskopulety.beer_rating.service.domain;

import java.util.List;

import jakarta.annotation.Nonnull;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BeerStatistics {

    private final int numberOfRatings;

    private final double averageValue;

    @Nonnull
    private final List<Rating> ratings;

}
