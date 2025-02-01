package cz.tomaskopulety.beer_rating.api.response;

import java.util.List;

import jakarta.annotation.Nonnull;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BeerStatisticsResponse {

    private final int numberOfRatings;

    private final double averageValue;

    @Nonnull
    private final List<RatingResponse> ratings;

}
