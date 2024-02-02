package app.controller.rest;

import app.controller.doc.PlayerRestDoc;
import app.entity.Player;
import app.entity.Trainer;
import app.exceptions.TeamManagerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.PlayerService;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "\u200B\uD83C\uDFC3\uD83C\uDFFDPLAYER TABLE\u200B\uD83C\uDFC3\uD83C\uDFFD", description = "List of methods to interact with Player table.")
@RestController
@RequestMapping("/player")
public class PlayerRestController implements PlayerRestDoc {
    private final PlayerService service;

    public PlayerRestController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Player> newPlayer(@RequestBody Player player){
        Player newPlayer = service.create(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }
    @PostMapping("/addPlayer-list")
    public ResponseEntity<List<Player>> newListOfPlayers(@RequestBody List<Player> players){
        return new ResponseEntity<>(service.createList(players), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long idPlayer){
        service.delete(idPlayer);
    }
    @GetMapping("/player-list/{idTeam}")
    public ResponseEntity<List<Player>> getPlayersByTeam(@PathVariable("idTeam") Long idTeam){
        return new ResponseEntity<>(service.getByTeam(idTeam), HttpStatus.OK);
    }
    @GetMapping("/top10-players-with-more-goals")
    public ResponseEntity<List<Player>> getOrderByGoals(){
        return new ResponseEntity<>(service.getOrderByGoals(), HttpStatus.OK);
    }
    @GetMapping("/last-match-before/{date}")
    public ResponseEntity<List<Player>> getPlayerWithLastMatchBefore(@PathVariable("date") LocalDate date){
        return new ResponseEntity<>(service.getPlayerWithLastMatchBefore(date), HttpStatus.OK);
    }
    @GetMapping("/with-more-goals/{idTeam}")
    public ResponseEntity<Player> getPlayerWithMoreGoalsOfTeam(@PathVariable("idTeam") Long idTeam){
        return new ResponseEntity<>(service.getPlayerWithMoreGoalsOfTeam(idTeam), HttpStatus.OK);
    }
    @PutMapping("/update") // Se actualizan todos los atributos.
    public ResponseEntity<Player> update(@RequestBody Player player){
        try {
            return new ResponseEntity<>(service.update(player), HttpStatus.OK);
        } catch (TeamManagerException e){
            return new ResponseEntity<>(player, HttpStatus.NOT_MODIFIED);
        }
    }
}
