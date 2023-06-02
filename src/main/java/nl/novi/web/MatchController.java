package nl.novi.web;

import nl.novi.model.Team;
import nl.novi.model.dto.MatchDTO;
import nl.novi.repository.TeamRepository;
import nl.novi.model.Match;
import nl.novi.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MatchController {

    @Autowired
    private TeamRepository teamRepository;
    @GetMapping("/addmatch")
    public String addmatch(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("match", new MatchDTO());
        return "addmatch";
    }

    @Autowired
    private MatchRepository matchRepository;
    @PostMapping("/addmatch")
    public String addMatch(@ModelAttribute("match")MatchDTO match) {
        Team one = teamRepository.getById(match.getTeamOne());
        Team two = teamRepository.getById(match.getTeamTwo());
        Match newMatch = new Match();
        newMatch.setTeamOne(one);
        newMatch.setTeamTwo(two);
        matchRepository.save(newMatch);
        return "redirect:/matches";
    }

    @PostMapping("/addwinner")
    public String addWinner(@ModelAttribute("match")Match match) {
        matchRepository.save(match);
        return "redirect:/login";
    }

    @GetMapping("/matches")
    public String showAll(Model model) {
        model.addAttribute("matches", matchRepository.findAll());
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("match", new Match());
        return "matches";
    }
}
