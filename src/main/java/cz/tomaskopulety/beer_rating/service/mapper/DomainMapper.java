package cz.tomaskopulety.beer_rating.service.mapper;

import java.util.UUID;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.database.module.BeerEntity;
import cz.tomaskopulety.beer_rating.database.module.RatingEntity;
import cz.tomaskopulety.beer_rating.service.SequenceGenerator;
import cz.tomaskopulety.beer_rating.service.domain.Beer;
import cz.tomaskopulety.beer_rating.externalservice.response.BreweryClientBeerResponse;
import cz.tomaskopulety.beer_rating.service.domain.Rating;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DomainMapper {

    private final SequenceGenerator sequenceGenerator;

    /**
     *
     * @param rating
     * @return
     */
    @Nonnull
    public RatingEntity map(@Nonnull Rating rating) {
        final RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setId(rating.getId() == null ? this.sequenceGenerator.generateSequence(RatingEntity.SEQUENCE_NAME) : rating.getId());
        ratingEntity.setBeerId(rating.getBeerId());
        ratingEntity.setValue(rating.getValue());
        ratingEntity.setNote(rating.getNote());
        return ratingEntity;
    }

    @Nonnull
    public Beer map(@Nonnull BeerEntity entity) {
        return Beer.builder()
                .id(entity.getId())
                .uid(UUID.fromString(entity.getUid()))
                .brand(entity.getBrand())
                .name(entity.getName())
                .style(entity.getStyle())
                .hop(entity.getHop())
                .yeast(entity.getYeast())
                .ibu(entity.getIbu())
                .malts(entity.getMalts())
                .alcoholPercentage(entity.getAlcoholPercentage())
                .blgDegree(entity.getBlgDegree())
                .build();
    }

    @Nonnull
    public Beer map(@Nonnull BreweryClientBeerResponse breweryClientBeerResponse) {
        return Beer.builder()
                .id(breweryClientBeerResponse.getId())
                .uid(UUID.fromString(breweryClientBeerResponse.getUid()))
                .brand(breweryClientBeerResponse.getBrand())
                .name(breweryClientBeerResponse.getName())
                .style(breweryClientBeerResponse.getStyle())
                .hop(breweryClientBeerResponse.getHop())
                .yeast(breweryClientBeerResponse.getYeast())
                .ibu(breweryClientBeerResponse.getIbu())
                .malts(breweryClientBeerResponse.getMalts())
                .alcoholPercentage(Double.parseDouble(breweryClientBeerResponse.getAlcohol().replace("%","")))
                .blgDegree(Double.parseDouble(breweryClientBeerResponse.getBlg().replace("°Blg","")))
                .build();
    }

    @Nonnull
    public BeerEntity map(@Nonnull Beer beer) {
        final BeerEntity beerEntity = new BeerEntity();
        beerEntity.setId(beer.getId() == null ? this.sequenceGenerator.generateSequence(BeerEntity.SEQUENCE_NAME) : beer.getId());
        beerEntity.setUid(beer.getUid().toString());
        beerEntity.setBrand(beer.getBrand());
        beerEntity.setName(beer.getName());
        beerEntity.setStyle(beer.getStyle());
        beerEntity.setHop(beer.getHop());
        beerEntity.setYeast(beer.getYeast());
        beerEntity.setIbu(beer.getIbu());
        beerEntity.setMalts(beer.getMalts());
        beerEntity.setAlcoholPercentage(beer.getAlcoholPercentage());
        beerEntity.setBlgDegree(beer.getBlgDegree());
        return beerEntity;
    }

    @Nonnull
    public Rating map(@Nonnull RatingEntity entity) {
        return Rating.builder()
                .id(entity.getId())
                .beerId(entity.getBeerId())
                .value(entity.getValue())
                .note(entity.getNote())
                .build();
    }

}
