package app.entity;

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
@Table(name = "player_report")
public class PlayerReport {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private int goals = 0;

    private int assists = 0;

    private int matches = 0;

    @Column(name = "red_cards")
    private int redCards = 0;

    @Column(name = "yellow_cards")
    private int yellowCards = 0;
}
