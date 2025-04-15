package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Country;

public record CreateCountryDto (String name, String continent) {
    public Country toCountry (){
        return new Country(name, continent);
    }
}
