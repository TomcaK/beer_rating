
package cz.tomaskopulety.beer_rating.database.module;

import jakarta.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "rating")
public class RatingEntity {

    @Transient
    public static final String SEQUENCE_NAME = "rating_sequence";

    @Id
    private long id;

    private long beerId;

    private int value;

    @Nullable
    private String note;

}
