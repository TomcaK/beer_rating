package cz.tomaskopulety.beer_rating;

import jakarta.annotation.Nonnull;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import cz.tomaskopulety.beer_rating.database.BeerRepository;
import cz.tomaskopulety.beer_rating.database.RatingRepository;
import cz.tomaskopulety.beer_rating.externalservice.BreweryClient;
import cz.tomaskopulety.beer_rating.service.BeerService;
import cz.tomaskopulety.beer_rating.service.DataService;
import cz.tomaskopulety.beer_rating.service.RatingService;
import cz.tomaskopulety.beer_rating.service.SequenceGenerator;
import cz.tomaskopulety.beer_rating.service.StatisticsService;
import cz.tomaskopulety.beer_rating.service.mapper.DomainMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestClient;

@Configuration
@EnableMongoRepositories
public class BeerRatingConfiguration {

    @Bean
    public RatingService ratingService(@Nonnull RatingRepository ratingRepository, @Nonnull BeerService beerService, @Nonnull DomainMapper domainMapper) {
        return new RatingService(ratingRepository, beerService, domainMapper);
    }

    @Bean
    public DomainMapper dbMapper(@Nonnull SequenceGenerator sequenceGenerator) {
        return new DomainMapper(sequenceGenerator);
    }

    @Bean
    @ConfigurationProperties(value = "beer-rating")
    public BeerRatingProperties beerRatingProperties() {
        return new BeerRatingProperties();
    }

    @Bean
    public MongoClient mongoClient(@Nonnull BeerRatingProperties beerRatingProperties) {
        ConnectionString connectionString = new ConnectionString(beerRatingProperties.getMongoDb().getDbConnectionString());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate(@Nonnull MongoClient mongoClient, @Nonnull BeerRatingProperties beerRatingProperties) {
        return new MongoTemplate(mongoClient, beerRatingProperties.getMongoDb().getDbName());
    }

    @Bean
    public SequenceGenerator sequenceGenerator(@Nonnull MongoOperations mongoOperations) {
        return new SequenceGenerator(mongoOperations);
    }

    @Bean
    public BeerService beerService(@Nonnull final BeerRepository beerRepository, @Nonnull final DomainMapper domainMapper) {
        return new BeerService(beerRepository, domainMapper);
    }

    @Bean
    public BreweryClient dataSourceClient(@Nonnull BeerRatingProperties beerRatingProperties) {
        final RestClient restClient = RestClient.create(
                beerRatingProperties.getDataSource()
                        .getUrl()
                        .toExternalForm());

        return new BreweryClient(restClient, beerRatingProperties().getDataSource());
    }

    @Bean
    public DataService dataService(@Nonnull BeerService beerService, @Nonnull DomainMapper domainMapper, @Nonnull BreweryClient breweryClient) {
        return new DataService(beerService, domainMapper, breweryClient);
    }

    @Bean
    public StatisticsService statisticsService(@Nonnull BeerService beerService, @Nonnull RatingService ratingService, @Nonnull DomainMapper dbMapper) {
        return new StatisticsService(beerService, ratingService, dbMapper);
    }

}
