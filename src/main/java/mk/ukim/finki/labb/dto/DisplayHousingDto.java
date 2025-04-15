package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.model.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHousingDto (String name, Category category, Host host, Integer numRooms){
    public static DisplayHousingDto from(Housing housing) {
        return new DisplayHousingDto(
                housing.getName(),
                housing.getCategory(),
                housing.getHost(),
                housing.getNumRooms()
        );
    }

    public Housing toHousing() {
        return new Housing(name, category, host, numRooms);
    }

    public static List<DisplayHousingDto> from(List<Housing> housings) {
        return housings.stream().map(DisplayHousingDto::from).collect(Collectors.toList());
    }
}
