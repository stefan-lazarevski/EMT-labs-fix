package mk.ukim.finki.labb.model.dto;

import lombok.Data;
import mk.ukim.finki.labb.model.enumerations.Category;

@Data
public class HousingDto {

    private String name;
    private Category category;
    private Long hostId;
    private Integer numRooms;

    public HousingDto(){
    }

    public HousingDto(String name, Category category, Long hostId, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.hostId = hostId;
        this.numRooms = numRooms;
    }

}
