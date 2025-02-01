package cz.tomaskopulety.beer_rating.api.mapper;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.api.request.RatingCreateRequest;
import cz.tomaskopulety.beer_rating.api.request.RatingUpdateRequest;
import cz.tomaskopulety.beer_rating.api.response.BeerResponse;
import cz.tomaskopulety.beer_rating.api.response.RatingResponse;
import cz.tomaskopulety.beer_rating.service.domain.Beer;
import cz.tomaskopulety.beer_rating.service.domain.Rating;
import cz.tomaskopulety.beer_rating.service.domain.RatingUpdate;

public class ApiMapper {

    @Nonnull
    public static Rating map(long beerId, @Nonnull RatingCreateRequest request) {
        return Rating.builder()
                .beerId(beerId)
                .value(request.getValue())
                .note(request.getNote())
                .build();
    }

    @Nonnull
    public static RatingUpdate map(long ratingId, @Nonnull RatingUpdateRequest request) {
        return RatingUpdate.builder()
                .id(ratingId)
                .value(request.getValue())
                .note(request.getNote())
                .build();
    }

    @Nonnull
    public static RatingResponse map(@Nonnull Rating rating) {
        return RatingResponse.builder()
                .id(rating.getId())
                .beerId(rating.getBeerId())
                .value(rating.getValue())
                .note(rating.getNote())
                .build();
    }

    @Nonnull
    public static BeerResponse map(@Nonnull Beer beer) {
        return BeerResponse.builder()
                .id(beer.getId())
                .uid(beer.getUid().toString())
                .brand(beer.getBrand())
                .name(beer.getName())
                .style(beer.getStyle())
                .hop(beer.getHop())
                .yeast(beer.getYeast())
                .ibu(beer.getIbu())
                .malts(beer.getMalts())
                .alcoholPercentage(beer.getAlcoholPercentage())
                .blgDegree(beer.getBlgDegree())
                .build();
    }

}
