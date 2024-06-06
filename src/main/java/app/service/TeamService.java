package app.service;

import app.dto.TeamDTO;
import app.dto.TeamJsonDTO;
import app.entity.Event;
import app.entity.Player;
import app.entity.Team;
import app.entity.Trainer;
import app.exceptions.TeamManagerException;
import app.repository.EventRepository;
import app.repository.PlayerRepository;
import app.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class TeamService {
    private final TeamRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TrainerService trainerService;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    public Page<Team> findAllOrderByCreationDate(int numPage, int size, String sortBy) {
        return repository.findAll(PageRequest.of(numPage, size, Sort.by(sortBy)));
    }

    public Team updateMembers(Team team, String user) throws TeamManagerException {
        Player player = this.playerService.findByUser(user);
        Trainer trainer = this.trainerService.findByUser(user);
        if (player != null) {
            player.setTeam(team);
            this.playerService.update(player);
        } else {
            trainer.setTeam(team);
            this.trainerService.update(trainer);
        }

        return team;
    }

    public Team deleteUserFromTeam(String user) throws TeamManagerException {
        Player player = this.playerService.findByUser(user);
        Trainer trainer = this.trainerService.findByUser(user);
        if (player != null) {
            player.setTeam(null);
            this.playerService.update(player);
        } else {
            trainer.setTeam(null);
            this.trainerService.update(trainer);
        }

        return null;
    }

    public Event createEvent(String user, Event event) {
        Team team = this.getByUser(user);
        event.setTeam(team);
        return this.eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        Event eventoBBDD = this.eventRepository.findById(event.getId()).orElse(null);
        if (eventoBBDD != null) {
            event.setTeam(eventoBBDD.getTeam());
            return this.eventRepository.save(event);
        } else {
            return null;
        }
    }

    public Team getByUser(String user) {
        Player player = this.playerService.findByUser(user);
        Trainer trainer = this.trainerService.findByUser(user);
        if (player != null) {
            return player.getTeam();
        } else {
            if (trainer != null) {

                return trainer.getTeam();
            }
        }
        return null;
    }

    public Event getNextEvent(int idTeam) {
        Team team = this.repository.findById((long) idTeam).orElse(null);

        if (team != null) {
            System.out.println(team);
            Event event = this.eventRepository.findClosestEvent(team.getId(), LocalDateTime.now()).orElse(null);
            System.out.println(event);
            return event;

        } else {
            return null;
        }
    }

    public Team update(Team team) {
        return this.repository.save(team);
    }

    public List<Event> getNextEvents(int idTeam) {
        Team team = this.repository.findById((long) idTeam).orElse(null);

        if (team != null) {
            System.out.println(team);
            return this.eventRepository.findNextEvents(team.getId(), LocalDateTime.now()).orElse(null);
        } else {
            return null;
        }
    }

    public Event getEventById(Long idEvento) {
        return this.eventRepository.findById(idEvento).orElse(null);
    }

    public Team getByCode(String code) {
        return this.repository.findByCode(code);
    }


    public Team create(Team team) {
        Random random = new Random();

        String code = "TM-" + random.nextInt(100, 999);
        while (this.getByCode(code) != null) {
            code = "TM-" + random.nextInt(100, 999);
        }

        team.setCode(code);
        return repository.save(team);
    }

    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            Team team = repository.findById(id).get();
            repository.delete(team);
        }
    }

    public TeamDTO getWithMorePlayers() {
        return repository.getTeamWithMorePlayers();
    }

    public Trainer getTrainer(Long idTeam) {
        return repository.getTrainer(idTeam);
    }

    public Team getByName(String name) {
        return repository.findByName(name);
    }

    public List<TeamJsonDTO> getAllToJson() {
        return repository.getAllToJSON();
    }


}
