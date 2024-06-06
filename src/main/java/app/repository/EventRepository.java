package app.repository;

import app.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT e FROM Event e WHERE e.fecha >= :ohy AND e.team.id = :team_id ORDER BY e.fecha ASC LIMIT 1")
    Optional<Event> findClosestEvent(@Param("team_id") Long idTeam,@Param("ohy") LocalDateTime ahora);


    @Query(value = "SELECT e FROM Event e WHERE e.fecha >= :ohy AND e.team.id = :team_id ORDER BY e.fecha ASC")
    Optional<List<Event>> findNextEvents(@Param("team_id") Long idTeam, @Param("ohy") LocalDateTime ahora);

}
