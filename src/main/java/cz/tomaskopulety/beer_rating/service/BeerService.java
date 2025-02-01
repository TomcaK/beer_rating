package cz.tomaskopulety.beer_rating.service;

import java.util.List;

import jakarta.annotation.Nonnull;

import cz.tomaskopulety.beer_rating.database.BeerRepository;
import cz.tomaskopulety.beer_rating.service.domain.Beer;
import cz.tomaskopulety.beer_rating.service.mapper.DomainMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class BeerService {

    @Nonnull
    private final BeerRepository beerRepository;

    @Nonnull
    private final DomainMapper mapper;

    @Nonnull
    public List<Beer> getBeers() {
        return this.beerRepository.findAll()
                .stream()
                .map(this.mapper::map)
                .toList();
    }

    public void saveBeers(@Nonnull List<Beer> beers) {
        int count = 0;
        for (Beer beer : beers) {
            this.beerRepository.save(this.mapper.map(beer));
            count++;
        }
        log.info("Saved {} beers from {} downloaded.", count, beers.size());
    }

    public boolean isDbEmpty() {
        return this.beerRepository.count() == 0;
    }

    public boolean existBeer(long beerId) {
        return this.beerRepository.existsById(beerId);
    }
}
