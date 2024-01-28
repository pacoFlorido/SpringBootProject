package app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    private int matches;

    private int redCards;

    private int yellowCards;

    @OneToOne
    @JoinColumn (name = "player_id", unique = true, nullable = false)
    private Player player;
}
