package app.service;

import app.dto.TeamDTO;
import app.dto.TeamJsonDTO;
import app.entity.Player;
import app.entity.Team;
import app.entity.Trainer;
import app.exceptions.TeamManagerException;
import app.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TrainerService trainerService;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    public Page<Team> findAllOrderByCreationDate(int numPage, int size, String sortBy) {
        return repository.findAll(PageRequest.of(numPage, size, Sort.by(sortBy)));
    }

    public Team updateMembers(Team team, String user) throws TeamManagerException {
        Player player = this.playerService.findByUser(user);
        Trainer trainer = this.trainerService.findByUser(user);
        if (player != null) {
            player.setTeam(team);
            this.playerService.update(player);
        } else {
            trainer.setTeam(team);
            this.trainerService.update(trainer);
        }

        return team;
    }

    public Team create(Team team) {
        return repository.save(team);
    }

    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            Team team = repository.findById(id).get();
            repository.delete(team);
        }
    }
    public TeamDTO getWithMorePlayers(){
        return repository.getTeamWithMorePlayers();
    }
    public Trainer getTrainer(Long idTeam){
        return repository.getTrainer(idTeam);
    }
    public Team getByName(String name){
        return repository.findByName(name);
    }
    public List<TeamJsonDTO> getAllToJson(){
        return repository.getAllToJSON();
    }
}
