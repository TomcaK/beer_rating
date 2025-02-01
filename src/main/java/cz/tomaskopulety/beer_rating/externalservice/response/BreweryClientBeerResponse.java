package cz.tomaskopulety.beer_rating.externalservice.response;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreweryClientBeerResponse {

    @Nullable
    private Long id;

    @Nonnull
    private String uid;

    @Nonnull
    private String brand;

    @Nonnull
    private String name;

    @Nonnull
    private String style;

    @Nonnull
    private String hop;

    @Nonnull
    private String yeast;

    @Nonnull
    private String malts;

    @Nonnull
    private String ibu;

    @Nonnull
    private String alcohol;

    @Nonnull
    private String blg;

}
