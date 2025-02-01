package cz.tomaskopulety.beer_rating.service.domain;

import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Beer {

    @Nullable
    private final Long id;

    @Nonnull
    private final UUID uid;

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

    @Nonnull
    private final Double alcoholPercentage;

    @Nonnull
    private final Double blgDegree;

}
