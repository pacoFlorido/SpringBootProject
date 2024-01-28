package app.controller.rest;

import app.entity.Player;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.PlayerService;

import java.util.List;

@Tag(name = "PLAYER TABLE\u200B\uD83C\uDFC3\uD83C\uDFFD", description = "List of methods to interact with Player table.")
@RestController
@RequestMapping("/player")
public class PlayerRestController{
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
}
