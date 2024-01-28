package app.service;

import app.entity.Team;
import app.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll(){
        return repository.findAll();
    }
    public Page<Team> findAllOrderByCreationDate(){
        return repository.findAll(PageRequest.of(0, 3, Sort.by("name")));
    }
}
