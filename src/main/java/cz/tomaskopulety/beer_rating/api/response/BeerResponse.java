package cz.tomaskopulety.beer_rating.api.response;

import jakarta.annotation.Nonnull;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BeerResponse {

    private final long id;

    @Nonnull
    private final String uid;

    @Nonnull
    private final String brand;

    @Nonnull
    private final String name;

    @Nonnull
    private final String style;

    @Nonnull
    private final String hop;

    @Nonnull
    private final String yeast;

    @Nonnull
    private final String malts;

    @Nonnull
    private final String ibu;

    private final double alcoholPercentage;

    private final double blgDegree;

}
