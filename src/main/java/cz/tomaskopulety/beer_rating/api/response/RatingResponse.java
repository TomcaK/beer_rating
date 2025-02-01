package cz.tomaskopulety.beer_rating.api.response;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RatingResponse {

    private final long id;

    private final long beerId;

    @Nonnull
    private final String beerName;

    private int value;

    @Nullable
    private String note;

}
