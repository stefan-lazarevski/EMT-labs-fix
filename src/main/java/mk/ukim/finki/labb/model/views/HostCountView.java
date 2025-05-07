package mk.ukim.finki.labb.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Data
@Entity
@Immutable
@Table(name = "host_count_by_country")
public class HostCountView {

    @Id
    private Long country_id;
    private String country_name;
    private Long host_count;
}
