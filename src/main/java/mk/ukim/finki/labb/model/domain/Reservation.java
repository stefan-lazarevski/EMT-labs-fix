package mk.ukim.finki.labb.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labb.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;


    @ManyToMany
    private List<Housing> houses;


//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_username")
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.SET_NULL)
    private User user;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation(User user) {
        this.dateCreated = LocalDateTime.now();
        this.houses =  new ArrayList<>();
        this.user = user;
        this.status = ReservationStatus.AVAILABLE;
    }

    public Reservation(){}
}
