package cz.tomaskopulety.beer_rating.api.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class StatisticsResponse {

    @NonNull
    private final List<BeerResponse> ratedBeers;

    @NonNull
    private final List<BeerResponse> unratedBeers;

}
