package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "trainer")
public class Trainer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private LocalDate birthday;

    @Column (nullable = false)
    private String nationality;

    @Column (name = "favourite_strategy")
    private String favouriteStrategy;

    @OneToOne
    @JoinColumn (name = "team_id")
    private Team team;
}
