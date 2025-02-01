package cz.tomaskopulety.beer_rating.database;

import cz.tomaskopulety.beer_rating.database.module.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<RatingEntity, Long> {
}
