package cz.tomaskopulety.beer_rating.database.module;

import jakarta.annotation.Nonnull;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "beer")
public class BeerEntity {

    @Transient
    public static final String SEQUENCE_NAME = "beer_sequence";

    @Id
    @Nonnull
    private Long id;

    @Nonnull
    private String uid;

    @Nonnull
    private String brand;

    @Nonnull
    private String name;

    @Nonnull
    private String style;

    @Nonnull
    private String hop;

    @Nonnull
    private String yeast;

    @Nonnull
    private String malts;

    @Nonnull
    private String ibu;

    @Nonnull
    private Double alcoholPercentage;

    @Nonnull
    private Double blgDegree;

}
