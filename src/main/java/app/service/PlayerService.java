package app.service;

import app.entity.Player;
import org.springframework.stereotype.Service;
import app.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Player> findAll(){
        return repository.findAll();
    }

    public Player create(Player player){
        return repository.save(player);
    }
}
