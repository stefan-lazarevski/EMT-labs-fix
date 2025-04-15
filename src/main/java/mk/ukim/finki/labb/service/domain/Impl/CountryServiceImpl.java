package mk.ukim.finki.labb.service.domain.Impl;

import mk.ukim.finki.labb.model.domain.Country;
import mk.ukim.finki.labb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public List<Country> findAll() {
        return null;
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.empty();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
