package cz.tomaskopulety.beer_rating.api.response;

import java.util.List;

import jakarta.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BeerResponses {

    @Nonnull
    private final List<BeerResponse> beers;
}
