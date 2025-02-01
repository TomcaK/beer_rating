package cz.tomaskopulety.beer_rating.externalservice;

import java.util.Collections;
import java.util.List;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.BeerRatingProperties;
import cz.tomaskopulety.beer_rating.externalservice.response.BreweryClientBeerResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@AllArgsConstructor
public class BreweryClient {

    @Nonnull
    private final RestClient restClient;

    @Nonnull
    private final BeerRatingProperties.DataSource dataSource;

    @Nonnull
    public List<BreweryClientBeerResponse> getBeers() {
        final RestClient.ResponseSpec responseSpec = this.restClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("size", dataSource.getDataSize()).build())
                .headers(
                        headers -> {
                            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                            headers.setContentType(MediaType.APPLICATION_JSON);
                        }
                )
                .retrieve();

        final List<BreweryClientBeerResponse> response = responseSpec.body(new ParameterizedTypeReference<>() {});

        return response == null
                ? Collections.emptyList()
                : response;
    }

}
