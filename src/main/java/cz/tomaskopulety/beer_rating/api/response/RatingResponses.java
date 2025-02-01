package cz.tomaskopulety.beer_rating.api.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class RatingResponses {

    @NonNull
    private final List<RatingResponse> ratings;

}
