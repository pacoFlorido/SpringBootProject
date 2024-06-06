package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false, unique = true)
    private String user;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private LocalDate birthday;

    @Column (nullable = false)
    private String nationality;

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn (name = "team_id")
    private Team team;
}
