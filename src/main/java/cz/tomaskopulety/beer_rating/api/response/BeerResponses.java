package cz.tomaskopulety.beer_rating.api.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class BeerResponses {

    @NonNull
    private final List<BeerResponse> beers;

}
