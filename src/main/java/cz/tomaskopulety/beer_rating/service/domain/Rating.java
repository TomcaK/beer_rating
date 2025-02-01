package cz.tomaskopulety.beer_rating.service.domain;

import jakarta.annotation.Nullable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Rating {

    @Nullable
    private final Long id;

    private final long beerId;

    @Nullable
    private final String beerName;

    private final int value;

    @Nullable
    private final String note;

}
