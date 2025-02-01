package cz.tomaskopulety.beer_rating.api;

import java.util.List;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.api.mapper.ApiMapper;
import cz.tomaskopulety.beer_rating.api.response.BeerResponses;
import cz.tomaskopulety.beer_rating.service.BeerService;
import cz.tomaskopulety.beer_rating.service.domain.Beer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cz.tomaskopulety.beer_rating.api.BeerController.BASE_URL;


@AllArgsConstructor
@RestController
@Validated
@RequestMapping(path = BASE_URL)
@Tag(name = "Beer")
public class BeerController {

    public static final String BASE_URL = "/api/beers";

    @Nonnull
    private final BeerService beerService;

    @Operation(summary = "Get all saved beers.", responses = {
            @ApiResponse(responseCode = "200", description = "Beers were returned successfully.", content = {@Content(schema = @Schema(implementation = BeerResponses.class))}),
    })
    @GetMapping
    public ResponseEntity<BeerResponses> getBeers() {
        final List<Beer> beers = this.beerService.getBeers();
        return ResponseEntity.ok(
                new BeerResponses(
                        beers.stream()
                                .map(ApiMapper::map)
                                .toList()
                )
        );
    }

}
