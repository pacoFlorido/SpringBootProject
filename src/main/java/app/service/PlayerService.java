package app.service;

import app.entity.Player;
import app.entity.PlayerReport;
import app.entity.Team;
import app.entity.Trainer;
import app.exceptions.ErrorCode;
import app.exceptions.TeamManagerException;
import app.repository.PlayerReportRepository;
import app.repository.TeamRepository;
import app.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.repository.PlayerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    @Autowired
    private TrainerRepository trainerRepository;

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

    public void deleteByUsuario(String usuario){
        Player player = this.findByUser(usuario);
        if (player != null){
            this.repository.delete(player);
        }
    }
    public List<Player> getByTeam(Long idTeam){
        Team team = teamRepository.findById(idTeam).get();
        return repository.findByTeam(team);
    }

    public List<Player> getByTeamOrderBy(Long idTeam, String orden){
        Team team = teamRepository.findById(idTeam).get();

        List<Player> players = new ArrayList<>();

        switch (orden) {
            case "goles":
                players = repository.findByTeamOrderByPlayerReport_GoalsDesc(team);
                break;
            case "asistencias":
                players = repository.findByTeamOrderByPlayerReport_AssistsDesc(team);
                break;
            case "partidos":
                players = repository.findByTeamOrderByPlayerReport_MatchesDesc(team);
                break;
            case "amarillas":
                players = repository.findByTeamOrderByPlayerReport_YellowCardsDesc(team);
                break;
            case "rojas":
                players = repository.findByTeamOrderByPlayerReport_RedCardsDesc(team);
                break;
            default:
                players = repository.findByTeam(team);
                break;
        }
        return players;

    }

    public List<Player> getOrderByGoals(){
        return repository.findTop10ByOrderByPlayerReport_GoalsDesc();
    }

    public Player getPlayerWithMoreGoalsOfTeam(Long teamId){
        return repository.findFirstByTeam_IdOrderByPlayerReport_GoalsDesc(teamId);
    }
    public Player update(Player player) throws TeamManagerException {
        if (player.getUser() == null){
            throw new TeamManagerException(ErrorCode.ID_NOT_FOUND, "No existe Player con ese ID.");
        }

        Player playerActualizar = this.repository.findByUser(player.getUser());
        playerActualizar.setPlayerReport(player.getPlayerReport());
        playerActualizar.setName(player.getName());
        playerActualizar.setAlias(player.getAlias());
        playerActualizar.setImage(player.getImage());
        return this.repository.save(playerActualizar);

    }

    public List<Player> get50conMasGoles() {
        return this.repository.findTop50ByOrderByPlayerReport_GoalsDesc();
    }
}
