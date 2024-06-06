package app.repository;

import app.entity.Player;
import app.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface PlayerRepository  extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);

    List<Player> findByTeamOrderByPlayerReport_GoalsDesc(Team team);
    List<Player> findByTeamOrderByPlayerReport_AssistsDesc(Team team);
    List<Player> findByTeamOrderByPlayerReport_MatchesDesc(Team team);
    List<Player> findByTeamOrderByPlayerReport_RedCardsDesc(Team team);
    List<Player> findByTeamOrderByPlayerReport_YellowCardsDesc(Team team);


    List<Player> findTop10ByOrderByPlayerReport_GoalsDesc();
    List<Player> findTop50ByOrderByPlayerReport_GoalsDesc();

    Player findFirstByTeam_IdOrderByPlayerReport_GoalsDesc(Long idTeam);

    Player findByUser(String user);
}
