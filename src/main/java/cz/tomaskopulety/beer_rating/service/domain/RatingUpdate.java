package cz.tomaskopulety.beer_rating.service.domain;

import jakarta.annotation.Nullable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RatingUpdate {

    private final long id;

    @Nullable
    private final Integer value;

    @Nullable
    private final String note;

}
