package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Country;
import mk.ukim.finki.labb.model.domain.Host;

public record CreateHostDto (String name, String surname, Country country) {
    public Host toHost(){
        return new Host(name, surname, country);
    }
}
