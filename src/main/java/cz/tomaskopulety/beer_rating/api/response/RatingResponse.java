package cz.tomaskopulety.beer_rating.api.response;

import jakarta.annotation.Nullable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RatingResponse {

    private final long id;

    private final long beerId;

    private int value;

    @Nullable
    private String note;

}
