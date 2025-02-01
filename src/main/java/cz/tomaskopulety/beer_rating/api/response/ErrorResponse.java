package cz.tomaskopulety.beer_rating.api.response;

import jakarta.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    @Nonnull
    private final HttpStatus error;

    @Nonnull
    private final String message;

}
