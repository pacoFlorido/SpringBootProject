package app.service;

import app.repository.PlayerReportRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerReportService {
    private final PlayerReportRepository repository;

    public PlayerReportService(PlayerReportRepository repository) {
        this.repository = repository;
    }
}
