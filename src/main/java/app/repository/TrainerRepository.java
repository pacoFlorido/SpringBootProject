package app.repository;

import app.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByUser(String user);

    List<Trainer> findByTeam_Id(Long id);


}
