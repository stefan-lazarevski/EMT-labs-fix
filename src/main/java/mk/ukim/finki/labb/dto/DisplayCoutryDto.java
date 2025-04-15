package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Country;
import mk.ukim.finki.labb.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCoutryDto (String name, String continent) {
    public static DisplayCoutryDto from(Country country) {
        return new DisplayCoutryDto(
                country.getName(),
                country.getContinent()
        );
    }
    public  Country toContinent(){
        return new Country(name, continent);
    }

    public static List<DisplayCoutryDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCoutryDto::from).collect(Collectors.toList());
    }
}
