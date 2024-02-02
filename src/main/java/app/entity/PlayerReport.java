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

    private int goals;

    private int assists;

    private int matches;

    @Column(name = "red_cards")
    private int redCards;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "last_match")
    private LocalDate lastMatch;
}
