package app.controller.doc;

import app.entity.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRestDoc {
    @Operation(
            summary = "Retrieve a list of players who have played before the given date."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<List<Player>> getPlayerWithLastMatchBefore(@Parameter(description = "Date Format: yyyy-mm-dd.") LocalDate date);
    @Operation(
            summary = "Retrieve all the Players of the database.",
            description = "Get All the Players of the API. The response is a list of Player objects."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<List<Player>> getAll();
    @Operation(
            summary = "Retrieve a Player who have the given Id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<Player> getById(Long id);
    @Operation(
            summary = "Creates a new Player on the database."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<Player> newPlayer(Player player);
    @Operation(
            summary = "Creates a new List of Players."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<List<Player>> newListOfPlayers(List<Player> players);
    @Operation(
            summary = "Delete Player by the given Id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    void delete(Long idPlayer);
    @Operation(
            summary = "Retrieve 10 Players with the most goals."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<List<Player>> getOrderByGoals();
    @Operation(
            summary = "Retrieve the Player with the most goals for a given Team."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<Player> getPlayerWithMoreGoalsOfTeam(Long idTeam);
    @Operation(
            summary = "Retrieve the List of Players for a given Team.",
            description = "Retrieve the List of Players by Team ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<List<Player>> getPlayersByTeam(Long idTeam);
    @Operation(
            summary = "Updates a Player.",
            description = "Delete the TEAM and the ID of PLAYER REPORT to make it work."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.", content = { @Content(schema = @Schema(implementation = Player.class))}),
            @ApiResponse(responseCode = "401", description = "Error.", content = @Content)
    })
    ResponseEntity<Player> update(Player player);
}
