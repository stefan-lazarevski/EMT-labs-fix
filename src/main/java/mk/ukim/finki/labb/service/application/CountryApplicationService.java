package mk.ukim.finki.labb.service.application;


import mk.ukim.finki.labb.dto.CreateCountryDto;
import mk.ukim.finki.labb.dto.DisplayCoutryDto;


import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<DisplayCoutryDto> findAll();

    Optional<DisplayCoutryDto> save(CreateCountryDto country);

    Optional<DisplayCoutryDto> findById(Long id);

    Optional<DisplayCoutryDto> update(Long id, CreateCountryDto country);

    void deleteById(Long id);
}
