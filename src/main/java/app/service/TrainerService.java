package app.service;

import app.entity.Player;
import app.entity.Team;
import app.exceptions.ErrorCode;
import app.exceptions.TeamManagerException;
import app.entity.Trainer;
import app.repository.TeamRepository;
import app.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository repository;
    private final TeamRepository teamRepository;

    public TrainerService(TrainerRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    public List<Trainer> getAll(){
        return  repository.findAll();
    }

    public Trainer create(Trainer trainer){
        return repository.save(trainer);
    }

    public Trainer update(Trainer trainer) throws TeamManagerException{
        if (trainer.getId() == null){
            throw new TeamManagerException(ErrorCode.ID_NOT_FOUND, "No existe Trainer con ese ID.");
        }
        return repository.save(trainer);
    }

    public Trainer setTeam(Long idTeam, Long idTrainer) {
        Trainer trainer = repository.findById(idTrainer).get();
        Team team = teamRepository.findById(idTeam).get();

        if (trainer == null){
            //throw new TeamManagerException(ErrorCode.ID_NOT_FOUND, "No existe Trainer con ese ID");
        } else if (team == null) {
            //throw new TeamManagerException(ErrorCode.ID_NOT_FOUND, "No existe Team con ese ID");
        } else {
            trainer.setTeam(team);
        }
        return repository.save(trainer);
    }
    public void delete(Long id){
        if (repository.findById(id).isPresent()){
            Trainer trainer = repository.findById(id).get();
            repository.delete(trainer);
        }
    }
}
