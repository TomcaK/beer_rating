package cz.tomaskopulety.beer_rating.service;

import java.util.Objects;

import cz.tomaskopulety.beer_rating.database.module.DatabaseSequenceEntity;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@AllArgsConstructor
public class SequenceGenerator {

    @NonNull
    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequenceEntity counter = this.mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequenceEntity.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
