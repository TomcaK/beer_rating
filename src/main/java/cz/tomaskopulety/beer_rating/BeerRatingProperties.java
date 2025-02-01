package cz.tomaskopulety.beer_rating;

import java.net.URL;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class BeerRatingProperties {

    @Nonnull
    @Valid
    private MongoDb mongoDb = new MongoDb();

    @Nonnull
    @Valid
    private DataSource dataSource = new DataSource();


    @Getter
    @Setter
    public static class MongoDb{

        @Nonnull
        private String dbConnectionString;

        @Nonnull
        private String dbName;

    }

    @Getter
    @Setter
    public static class DataSource{

        @Nonnull
        private URL url;

        private int dataSize;

    }

}
