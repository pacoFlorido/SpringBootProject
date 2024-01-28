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
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false, unique = true)
    private String alias;

    @Column (nullable = false)
    private String nationality;

    @Column (nullable = false)
    private LocalDate birthday;

    @JsonBackReference
    @ManyToOne
    @JoinColumn (name = "team_id")
    private Team team;
}
