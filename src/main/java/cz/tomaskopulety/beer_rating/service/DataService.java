package cz.tomaskopulety.beer_rating.service;

import java.util.List;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.externalservice.BreweryClient;
import cz.tomaskopulety.beer_rating.service.domain.Beer;
import cz.tomaskopulety.beer_rating.service.mapper.DomainMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@AllArgsConstructor
@Slf4j
public class DataService {

    @Nonnull
    private final BeerService beerService;

    @Nonnull
    private final DomainMapper domainMapper;

    @Nonnull
    private final BreweryClient breweryClient;

    @EventListener(ApplicationReadyEvent.class)
    private void downloadData() {
        log.info("Downloading data...");
        this.downloadBeers();
        log.info("Downloading data complete.");
    }

    private void downloadBeers() {
        if (!this.beerService.isDbEmpty()) {
            log.info("Beers saved already.");
            return;
        }

        final List<Beer> beers = breweryClient.getBeers()
                .stream()
                .map(this.domainMapper::map)
                .toList();
        this.beerService.saveBeers(beers);
    }

}
