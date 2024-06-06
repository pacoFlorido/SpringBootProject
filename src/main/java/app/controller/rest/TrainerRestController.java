package app.controller.rest;

import app.entity.Player;
import app.entity.Team;
import app.entity.Trainer;
import app.exceptions.TeamManagerException;
import app.service.TrainerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "\uD83C\uDFC5TRAINER TABLE\uD83C\uDFC5", description = "List of methods to interact with Trainer table.")
@RestController
@RequestMapping("/trainer")
public class TrainerRestController {

    private final TrainerService service;

    public TrainerRestController(TrainerService service) {
        this.service = service;
    }

    @GetMapping("/{user}")
    public ResponseEntity<Trainer> getByUsuario(@PathVariable("user") String user){
        return new ResponseEntity<>(service.findByUser(user), HttpStatus.OK);
    }

    @GetMapping("/trainer-list/{idTeam}")
    public ResponseEntity<List<Trainer>> getTrainersByTeam(@PathVariable("idTeam") Long idTeam){
        return new ResponseEntity<>(service.getByTeam(idTeam), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{user}")
    public void deleteByUsuario(@PathVariable("user") String user){
        this.service.deleteByUsuario(user);
    }






    @GetMapping("/all") // Muestra todos los Entrenadores.
    public ResponseEntity<List<Trainer>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
    @PostMapping // Crea un nuevo entrenador.
    public ResponseEntity<Trainer> newTrainer(@RequestBody Trainer trainer){
        return new ResponseEntity<>(service.create(trainer), HttpStatus.OK);
    }
    @PutMapping("/update") // Se actualizan todos los atributos.
    public ResponseEntity<Trainer> update(@RequestBody Trainer trainer){
        try {
            return new ResponseEntity<>(service.update(trainer), HttpStatus.OK);
        } catch (TeamManagerException e){
            return new ResponseEntity<>(trainer, HttpStatus.NOT_MODIFIED);
        }
    }

    @PatchMapping("/set-team/{idTrainer}/{idTeam}") // Asigna un equipo a un entrenador mediante las Id del entrenador y la del equipo.
    public ResponseEntity<Trainer> setTeam(@PathVariable("idTeam") Long teamId, @PathVariable("idTrainer") Long trainerId){
        return new ResponseEntity<>(service.setTeam(teamId, trainerId), HttpStatus.OK);
    }
}
