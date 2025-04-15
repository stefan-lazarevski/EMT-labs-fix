package mk.ukim.finki.labb.dto;

import mk.ukim.finki.labb.model.domain.Host;
import mk.ukim.finki.labb.model.domain.Housing;
import mk.ukim.finki.labb.model.enums.Category;

public record CreateHousingDto(String name, Category category, Host host, Integer numRooms) {
    public Housing toHousing(){
        return new Housing(name, category, host, numRooms);
    }
}
