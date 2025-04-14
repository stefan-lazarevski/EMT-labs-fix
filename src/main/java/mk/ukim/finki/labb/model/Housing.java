package mk.ukim.finki.labb.model;

import jakarta.persistence.*;


import lombok.Data;
import mk.ukim.finki.labb.model.enumerations.Category;

@Data
@Entity
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;


    public Housing(){
    }

    public Housing(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
