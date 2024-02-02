package app.controller.rest;

import app.dto.TeamDTO;
import app.entity.Team;
import app.service.TeamService;
import app.util.TeamSortBy;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Team>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/all/paginated")
    public ResponseEntity<Page<Team>> getAllOrderBy(@RequestParam (required = true) int numPage,
                                                    @RequestParam (defaultValue = "2", required = false) int size,
                                                    @RequestParam (defaultValue = "name", required = false) TeamSortBy sortBy){
        return new ResponseEntity<>(service.findAllOrderByCreationDate(numPage, size, String.valueOf(sortBy)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Team> newTeam(@RequestBody Team team){
        return new ResponseEntity<>(service.create(team), HttpStatus.OK);
    }

    @GetMapping("/with-more-players")
    public ResponseEntity<TeamDTO> getWithMorePlayers(){
        return new ResponseEntity<>(service.getWithMorePlayers(), HttpStatus.OK);
    }
}
