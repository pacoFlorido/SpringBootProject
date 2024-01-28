package app.repository;

import app.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository  extends JpaRepository<Player, Long> {
}
