package mk.ukim.finki.labb.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
@Table(name = "housing_count_by_host")
public class HousingCountView {

    @Id
    private Long host_id;
    private String name;
    private String surname;
    private Long housingCount;
}
