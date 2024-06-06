package app.controller.rest;

import app.dto.TeamDTO;
import app.entity.Event;
import app.entity.Team;
import app.exceptions.TeamManagerException;
import app.service.TeamService;
import app.util.TeamSortBy;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Tag(name = "\uD83C\uDFBDTEAM TABLE\uD83C\uDFBD", description = "List of methods to interact with Team table.")
@RestController
@RequestMapping("/team")
public class TeamRestController {
    private final TeamService service;

    public TeamRestController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/addUser/{user}")
    public ResponseEntity<Team> updateMembers(@RequestBody Team team, @PathVariable String user) {
        try {
            return new ResponseEntity<>(this.service.updateMembers(team, user), HttpStatus.OK);
        } catch (TeamManagerException e) {
            System.out.println("Error updateando EQUIPO");
            System.out.println(e.getDetailMessage());
        }
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<Team> update(@RequestBody Team team) {
        return new ResponseEntity<>(this.service.update(team), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Team> getTeamByCode(@PathVariable String code) {
        return new ResponseEntity<>(this.service.getByCode(code), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> newTeam(@RequestBody Team team) {
        return new ResponseEntity<>(service.create(team), HttpStatus.OK);
    }

    @GetMapping("/getBy/{user}")
    public ResponseEntity<Team> getTeamByUser(@PathVariable String user) {
        return new ResponseEntity<>(this.service.getByUser(user), HttpStatus.OK);
    }

    @GetMapping("/getNextEvent/{idTeam}")
    public ResponseEntity<Event> getNextEvent(@PathVariable("idTeam") int idTeam) {
        Event event = this.service.getNextEvent(idTeam);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return null;
        }
    }

    @GetMapping("/getNextEvents/{idTeam}")
    public ResponseEntity<List<Event>> getNextEvents(@PathVariable("idTeam") int idTeam) {
        List<Event> event = this.service.getNextEvents(idTeam);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping("/event/{user}")
    public ResponseEntity<Event> createEvent(@PathVariable("user") String user,
                                             @RequestBody Event event) {
        return new ResponseEntity<>(this.service.createEvent(user, event), HttpStatus.OK);
    }

    @PutMapping("/event")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        return new ResponseEntity<>(this.service.updateEvent(event), HttpStatus.OK);
    }

    @GetMapping("/getEventById/{idEvento}")
    public ResponseEntity<Event> getEventById(@PathVariable Long idEvento) {
        return new ResponseEntity<>(this.service.getEventById(idEvento), HttpStatus.OK);
    }

    @PatchMapping("/deleteUserFromTeam/{user}")
    public ResponseEntity<Team> eliminarUsuarioDeEquipo(@PathVariable("user") String user) {
        try {
            return new ResponseEntity<>(this.service.deleteUserFromTeam(user), HttpStatus.OK);
        } catch (TeamManagerException e) {
            System.out.println("Error eliminando usuario del equipo: " + e.getDetailMessage());
            return null;
        }
    }





    @GetMapping("/all/paginated")
    public ResponseEntity<Page<Team>> getAllOrderBy(@RequestParam(required = true) int numPage,
                                                    @RequestParam(defaultValue = "2", required = false) int size,
                                                    @RequestParam(defaultValue = "name", required = false) TeamSortBy sortBy) {
        return new ResponseEntity<>(service.findAllOrderByCreationDate(numPage, size, String.valueOf(sortBy)), HttpStatus.OK);
    }

    @GetMapping("/with-more-players")
    public ResponseEntity<TeamDTO> getWithMorePlayers() {
        return new ResponseEntity<>(service.getWithMorePlayers(), HttpStatus.OK);
    }

    @GetMapping("/to-json/all")
    public ResponseEntity<String> teamsToJSON() {
        String json = new Gson().toJson(service.getAllToJson());
        Path path = Paths.get("teams.json");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.writeString(path, json, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creando el fichero JSON.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(path.toString(), HttpStatus.OK);
    }
}
