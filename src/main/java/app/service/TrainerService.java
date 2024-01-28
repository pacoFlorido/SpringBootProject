package app.service;

import app.repository.TrainerRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private final TrainerRepository repository;

    public TrainerService(TrainerRepository repository) {
        this.repository = repository;
    }
}
