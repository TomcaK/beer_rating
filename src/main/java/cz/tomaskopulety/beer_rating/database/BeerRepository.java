package cz.tomaskopulety.beer_rating.database;

import java.util.Optional;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.database.module.BeerEntity;
import cz.tomaskopulety.beer_rating.database.projection.BeerNameProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends MongoRepository<BeerEntity, Long> {

    @Nonnull
    Optional<BeerNameProjection> findNameById(@Nonnull Long id);

}
