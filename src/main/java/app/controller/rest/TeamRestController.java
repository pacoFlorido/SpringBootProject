package app.controller.rest;

import app.entity.Team;
import app.service.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "TEAM TABLE \uD83C\uDFBD", description = "List of methods to interact with Team table.")
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

    @GetMapping("/all/orderBy")
    public ResponseEntity<Page<Team>> getAllOrderBy(){
        return new ResponseEntity<>(service.findAllOrderByCreationDate(), HttpStatus.OK);
    }
}
