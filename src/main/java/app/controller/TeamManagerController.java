package app.controller;

import app.dto.PlayerDTO;
import app.entity.Player;
import app.entity.PlayerReport;
import app.entity.Team;
import app.exceptions.TeamManagerException;
import app.service.PlayerService;
import app.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class TeamManagerController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    @GetMapping()
    public String showHome(){
        return "index";
    }
    @GetMapping("/teams")
    public String getList(Model model){
        model.addAttribute("teams", teamService.findAll());
        return "teams/teams";
    }
    @GetMapping("/teams/trainer/{id}")
    public String seeTrainer(@PathVariable("id") Long idTeam, Model model){
        model.addAttribute("trainer", teamService.getTrainer(idTeam));
        return "teams/trainer";
    }
    @GetMapping("/teams/players/{id}")
    public String seePlayers(@PathVariable("id") Long idTeam, Model model){
        model.addAttribute("players", playerService.getByTeam(idTeam));
        return "teams/players";
    }

    @GetMapping("/players")
    public String getAllPlayers(Model model){
        model.addAttribute("players", playerService.findAll());
        return "players/players";
    }

    @GetMapping("/players/new")
    public String showFormCreate(Model model){
        PlayerDTO player = new PlayerDTO();
        model.addAttribute("player", player);
        model.addAttribute("teams",teamService.findAll());
        return "players/add";
    }

    @PostMapping("/players")
    public String createPlayer(@ModelAttribute("player") PlayerDTO player){
        Team team = teamService.getByName(player.getTeam());

        Player newPlayer = new Player();
        newPlayer.setName(player.getName());
        newPlayer.setTeam(team);
        newPlayer.setAlias(player.getAlias());
        newPlayer.setNationality(player.getNationality());
        newPlayer.setBirthday(player.getBirthday());
        newPlayer.setPlayerReport(new PlayerReport());
        playerService.create(newPlayer);
        return "redirect:/view/players";
    }
    @GetMapping("/players/delete/{id}")
    public String deleteById(@PathVariable("id") Long idPlayer) {
        playerService.delete(idPlayer);
        return "redirect:/view/players";
    }
    @GetMapping("/players/update/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") Long idPlayer){
        PlayerDTO player = new PlayerDTO(playerService.findById(idPlayer));
        model.addAttribute("player", player);
        model.addAttribute("teams", teamService.findAll());
        return "players/update";
    }

    @PostMapping("/players/{id}")
    public String updatePlayer(@ModelAttribute("player") PlayerDTO player, @PathVariable("id") Long id){
        Team team = teamService.getByName(player.getTeam());

        Player playerExisting = playerService.findById(id);

        playerExisting.setName(player.getName());
        playerExisting.setTeam(team);
        playerExisting.setAlias(player.getAlias());
        playerExisting.setNationality(player.getNationality());
        playerExisting.getPlayerReport().setGoals(player.getGoals());
        playerExisting.getPlayerReport().setAssists(player.getAssists());
        playerExisting.getPlayerReport().setMatches(player.getMatches());
        playerExisting.getPlayerReport().setRedCards(player.getRedCards());
        playerExisting.getPlayerReport().setYellowCards(player.getYellowCards());
        try {
            playerService.update(playerExisting);
        } catch (TeamManagerException e) {
            System.out.println(e.getDetailMessage());
        }
        return "redirect:/view/players";
    }
}
