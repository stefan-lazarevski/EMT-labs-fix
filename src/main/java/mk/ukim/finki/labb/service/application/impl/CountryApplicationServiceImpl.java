package mk.ukim.finki.labb.service.application.impl;

import mk.ukim.finki.labb.dto.CreateCountryDto;
import mk.ukim.finki.labb.dto.DisplayCoutryDto;
import mk.ukim.finki.labb.dto.DisplayHostDto;
import mk.ukim.finki.labb.service.application.CountryApplicationService;
import mk.ukim.finki.labb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCoutryDto> findAll() {
        return countryService.findAll()
                .stream()
                .map(DisplayCoutryDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayCoutryDto> findById(Long id) {
        return countryService.findById(id)
                .map(DisplayCoutryDto::from);
    }

    @Override
    public Optional<DisplayCoutryDto> save(CreateCountryDto country) {
        return countryService.save(country.toCountry())
                .map(DisplayCoutryDto::from);
    }

    @Override
    public Optional<DisplayCoutryDto> update(Long id, CreateCountryDto country) {
        return countryService.update(id, country.toCountry())
                .map(DisplayCoutryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
