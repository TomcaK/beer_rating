package cz.tomaskopulety.beer_rating.database;

import cz.tomaskopulety.beer_rating.database.module.BeerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends MongoRepository<BeerEntity, Long> {
}
