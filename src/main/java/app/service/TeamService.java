package app.service;

import app.dto.TeamDTO;
import app.dto.TeamJsonDTO;
import app.entity.Player;
import app.entity.Team;
import app.entity.Trainer;
import app.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    public Page<Team> findAllOrderByCreationDate(int numPage, int size, String sortBy) {
        return repository.findAll(PageRequest.of(numPage, size, Sort.by(sortBy)));
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
