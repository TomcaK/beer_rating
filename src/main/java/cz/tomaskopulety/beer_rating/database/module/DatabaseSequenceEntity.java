package cz.tomaskopulety.beer_rating.database.module;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "database_sequence")
public class DatabaseSequenceEntity {

    @Id
    @NonNull
    private String id;

    private long seq;

}
