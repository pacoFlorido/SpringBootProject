package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "team")
public class Team {
    //TODO Completar entidades.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column (name = "creation_date", nullable = false)
    private LocalDate creationDate;

    private String city;

    private Double budget;

    @JsonManagedReference
    @OneToMany (mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Player> players;

}
