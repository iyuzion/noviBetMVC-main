package nl.novi.web;

import nl.novi.model.Team;
import nl.novi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchController {

    @Autowired
    private TeamRepository teamRepository;
    @GetMapping("/addmatch")
    public String addmatch(Model model) {
        model.addAttribute("teamOne", teamRepository.findAll()
                .stream()
                .map(Team::getName)
                .toList());
        return "addmatch";
    }
}
