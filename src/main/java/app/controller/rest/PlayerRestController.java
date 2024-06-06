package app.controller.rest;

import app.controller.doc.PlayerRestDoc;
import app.entity.Player;
import app.entity.Team;
import app.entity.Trainer;
import app.exceptions.TeamManagerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.PlayerService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/{user}")
    public ResponseEntity<Player> getByUsuario(@PathVariable("user") String user){
        return new ResponseEntity<>(service.findByUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{user}")
    public void deleteByUsuario(@PathVariable("user") String user){
        this.service.deleteByUsuario(user);
    }

    @PostMapping
    public ResponseEntity<Player> newPlayer(@RequestBody Player player){
        Player newPlayer = service.create(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }

    @GetMapping("/get50withMoreGoals")
    public ResponseEntity<List<Player>> get50WithMoreGoals(){
        return new ResponseEntity<>(this.service.get50conMasGoles(), HttpStatus.OK);
    }

    @GetMapping("/player-list/{idTeam}")
    public ResponseEntity<List<Player>> getPlayersByTeam(@PathVariable("idTeam") Long idTeam){
        return new ResponseEntity<>(service.getByTeam(idTeam), HttpStatus.OK);
    }

    @GetMapping("/player-list/{idTeam}/{orden}")
    public ResponseEntity<List<Player>> getPlayersByTeamOrderBy(@PathVariable("idTeam") Long idTeam, @PathVariable("orden") String orden){
        System.out.println(service.getByTeamOrderBy(idTeam, orden));
        return new ResponseEntity<>(service.getByTeamOrderBy(idTeam, orden), HttpStatus.OK);
    }
    @PutMapping("/update") // Se actualizan todos los atributos.
    public ResponseEntity<Player> update(@RequestBody Player player){
        try {
            return new ResponseEntity<>(service.update(player), HttpStatus.OK);
        } catch (TeamManagerException e){
            return new ResponseEntity<>(player, HttpStatus.NOT_MODIFIED);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
    @PostMapping("/addPlayer-list")
    public ResponseEntity<List<Player>> newListOfPlayers(@RequestBody List<Player> players){
        return new ResponseEntity<>(service.createList(players), HttpStatus.OK);
    }

    @GetMapping("/top10-players-with-more-goals")
    public ResponseEntity<List<Player>> getOrderByGoals(){
        return new ResponseEntity<>(service.getOrderByGoals(), HttpStatus.OK);
    }
    @GetMapping("/with-more-goals/{idTeam}")
    public ResponseEntity<Player> getPlayerWithMoreGoalsOfTeam(@PathVariable("idTeam") Long idTeam){
        return new ResponseEntity<>(service.getPlayerWithMoreGoalsOfTeam(idTeam), HttpStatus.OK);
    }
}
