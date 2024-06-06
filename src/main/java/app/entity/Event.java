package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titlte")
    private String titulo;
    @Column(name = "description")
    private String descripcion;
    @Column(name = "date")
    private LocalDateTime fecha;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "team_id")
    private Team team;

}
