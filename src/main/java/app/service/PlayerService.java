package app.service;

import app.entity.Player;
import app.entity.PlayerReport;
import app.entity.Team;
import app.entity.Trainer;
import app.exceptions.ErrorCode;
import app.exceptions.TeamManagerException;
import app.repository.PlayerReportRepository;
import app.repository.TeamRepository;
import org.springframework.stereotype.Service;
import app.repository.PlayerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final TeamRepository teamRepository;

    public PlayerService(PlayerRepository repository, PlayerReportRepository playerReportRepository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    public Player findByUser(String user){
        return this.repository.findByUser(user);
    }
    public Player findById(Long id){
        return repository.findById(id).orElse(null);
    }
    public List<Player> findAll(){
        return repository.findAll();
    }
    public Player create(Player player){
        player.setPlayerReport(new PlayerReport());
        return repository.save(player);
    }
    public List<Player> createList(List<Player> players){
        List<Player> createdPlayers = new ArrayList<>();
        players.forEach(player -> {
            createdPlayers.add(repository.save(player));
        });
        return createdPlayers;
    }
    public void delete(Long id){
        if (repository.findById(id).isPresent()){
            Player player = repository.findById(id).get();
            repository.delete(player);
        }
    }
    public List<Player> getByTeam(Long idTeam){
        Team team = teamRepository.findById(idTeam).get();
        return repository.findByTeam(team);
    }
    public List<Player> getOrderByGoals(){
        return repository.findTop10ByOrderByPlayerReport_GoalsDesc();
    }

    public Player getPlayerWithMoreGoalsOfTeam(Long teamId){
        return repository.findFirstByTeam_IdOrderByPlayerReport_GoalsDesc(teamId);
    }
    public Player update(Player player) throws TeamManagerException {
        if (player.getId() == null){
            throw new TeamManagerException(ErrorCode.ID_NOT_FOUND, "No existe Player con ese ID.");
        }
        return repository.save(player);
    }
}
