package nl.novi.web;

import nl.novi.model.Team;
import nl.novi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/addteam")
    public String addteams(Model model) {
        model.addAttribute("Team", new Team());
        return "addteam";
    }

    @GetMapping("/teams")
    public String showAll(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "teams";
    }

    @PostMapping("/addteam")
    public String addUser(Team team) {
        teamRepository.save(team);
        return "redirect:/teams";
    }
}
