package cz.tomaskopulety.beer_rating.api;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import cz.tomaskopulety.beer_rating.api.mapper.ApiMapper;
import cz.tomaskopulety.beer_rating.api.request.RatingCreateRequest;
import cz.tomaskopulety.beer_rating.api.request.RatingUpdateRequest;
import cz.tomaskopulety.beer_rating.api.response.ErrorResponse;
import cz.tomaskopulety.beer_rating.api.response.RatingResponse;
import cz.tomaskopulety.beer_rating.api.response.RatingResponses;
import cz.tomaskopulety.beer_rating.service.RatingService;
import cz.tomaskopulety.beer_rating.service.domain.Rating;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cz.tomaskopulety.beer_rating.api.RatingController.BASE_URL;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping(path = BASE_URL)
@Tag(name = "Rating")
public class RatingController {

    public static final String BASE_URL = "/api/ratings";

    @Nonnull
    private final RatingService ratingService;

    @Operation(summary = "Create rating for selected beer.", responses = {
            @ApiResponse(responseCode = "201", description = "Rating was created successfully.", content = {@Content(schema = @Schema(implementation = RatingResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Some of parameters are wrong.", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping(path = "/{beerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingResponse> createRating(
            @Parameter(description = "Identifier of beer.", example = "8717") @PathVariable long beerId,
            @RequestBody @Valid @NotNull RatingCreateRequest request
    ) {
        final Rating newRating = this.ratingService.createRating(ApiMapper.map(beerId, request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiMapper.map(newRating));
    }

    @Operation(summary = "Get all saved rating.", responses = {
            @ApiResponse(responseCode = "200", description = "Ratings were returned successfully.", content = {@Content(schema = @Schema(implementation = RatingResponses.class))}),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingResponses> getRatings() {
        final List<Rating> ratings = this.ratingService.getRatings();
        return ResponseEntity.ok(
                 new RatingResponses(
                                ratings.stream()
                                        .map(ApiMapper::map)
                                        .toList()
                 )
        );
    }

    @Operation(summary = "Update existing rating.", responses = {
            @ApiResponse(responseCode = "202", description = "Update of rating was accepted.", content = {@Content(schema = @Schema(implementation = RatingResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Some of parameters are wrong.", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PatchMapping(path = "/{ratingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingResponse> updateRating(
            @Parameter(description = "Identifier of rating.", example = "1") @PathVariable long ratingId,
            @RequestBody @Valid @NotNull RatingUpdateRequest request) {
        final Rating updatedRating = this.ratingService.updateRating(ApiMapper.map(ratingId, request));
        return ResponseEntity.accepted().body(ApiMapper.map(updatedRating));
    }

}
