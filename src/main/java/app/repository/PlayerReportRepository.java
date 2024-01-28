package app.repository;

import app.entity.PlayerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerReportRepository extends JpaRepository<PlayerReport, Long> {
}
