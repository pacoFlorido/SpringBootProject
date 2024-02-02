package app.controller.doc;

import app.entity.Player;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRestDoc {
    @Operation(
            summary = "Retrieve a list of players who have played before the given date.",
            description = "Date Format: yyyy-mm-dd."
    )
    ResponseEntity<List<Player>> getPlayerWithLastMatchBefore(LocalDate date);
    @Operation(
            summary = "Retrieve all the Players of the database.",
            description = "Get All the Players of the API. The response is a list of Player objects."
    )
    ResponseEntity<List<Player>> getAll();
    @Operation(
            summary = "Retrieve a Player who have the given Id."
    )
    ResponseEntity<Player> getById(Long id);
    @Operation(
            summary = "Creates a new Player on the database."
    )
    ResponseEntity<Player> newPlayer(Player player);
    @Operation(
            summary = "Creates a new List of Players."
    )
    ResponseEntity<List<Player>> newListOfPlayers(List<Player> players);
    @Operation(
            summary = "Delete Player by the given Id."
    )
    void delete(Long idPlayer);
    @Operation(
            summary = "Retrieve 10 Players with the most goals."
    )
    ResponseEntity<List<Player>> getOrderByGoals();
    @Operation(
            summary = "Retrieve the Player with the most goals for a given Team."
    )
    ResponseEntity<Player> getPlayerWithMoreGoalsOfTeam(Long idTeam);
    @Operation(
            summary = "Retrieve the List of Players for a given Team."
    )
    ResponseEntity<List<Player>> getPlayersByTeam(Long idTeam);
    @Operation(
            summary = "Updates a Player.",
            description = "Delete the TEAM JSON and the ID of PLAYER REPORT to make it work."
    )
    ResponseEntity<Player> update(Player player);
}
