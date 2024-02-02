package app.dto;

import app.entity.Player;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PlayerDTO{
    private Long id;
    private String name;
    private String alias;
    private String nationality;
    private LocalDate birthday;
    private String team;
    private int goals;
    private int assists;
    private int matches;
    private int redCards;
    private int yellowCards;
    public PlayerDTO(){}

    public PlayerDTO(Player player){
        this.id = player.getId();
        this.name = player.getName();
        this.alias = player.getAlias();
        this.nationality = player.getNationality();
        this.birthday = player.getBirthday();
        this.team = player.getTeam().getName();
        this.goals = player.getPlayerReport().getGoals();
        this.assists = player.getPlayerReport().getAssists();
        this.matches = player.getPlayerReport().getMatches();
        this.redCards = player.getPlayerReport().getRedCards();
        this.yellowCards = player.getPlayerReport().getYellowCards();
    }
}
