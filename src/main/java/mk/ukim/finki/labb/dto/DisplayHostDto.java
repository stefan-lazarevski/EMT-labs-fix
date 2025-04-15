package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Country;
import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.model.domain.Housing;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDto (String name, String surname, Country country){
    public static DisplayHostDto from(Host host){
        return new DisplayHostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry()
        );
    }
    public Host toHost() {
        return new Host(name, surname, country);
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }
}
