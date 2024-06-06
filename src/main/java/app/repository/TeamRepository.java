package app.repository;

import app.dto.TeamDTO;
import app.dto.TeamJsonDTO;
import app.entity.Team;
import app.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT new app.dto.TeamDTO(t.name, COUNT(p)) FROM Team t JOIN Player p ON t.id = p.team.id GROUP BY t.name ORDER BY COUNT(p) DESC LIMIT 1")
    TeamDTO getTeamWithMorePlayers();
    @Query("SELECT tt FROM Team t JOIN Trainer tt ON t.id = tt.team.id WHERE t.id = :idTeam")
    Trainer getTrainer(Long idTeam);
    Team findByName(String name);

    @Query("SELECT new app.dto.TeamJsonDTO(t) FROM Team t")
    List<TeamJsonDTO> getAllToJSON();

    Team findByCode(String code);

}
