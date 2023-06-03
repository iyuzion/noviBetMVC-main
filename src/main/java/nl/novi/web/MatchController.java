package nl.novi.web;

import nl.novi.model.Team;
import nl.novi.model.dto.MatchDTO;
import nl.novi.repository.TeamRepository;
import nl.novi.model.Match;
import nl.novi.repository.MatchRepository;
import nl.novi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

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
    public String addMatch(@ModelAttribute("match")MatchDTO matchdto) {
        Team one = teamRepository.getById(matchdto.getTeamOne());
        Team two = teamRepository.getById(matchdto.getTeamTwo());
        Match Match = new Match();
        Match.setTeamOne(one);
        Match.setTeamTwo(two);
        matchRepository.save(Match);
        return "redirect:/matches";
    }

    @PostMapping("/addwinner")
    public String addWinner(@ModelAttribute("match")MatchDTO matchDTO) {
        Match match = matchRepository.getById(matchDTO.getId());
        match.setWinner(teamRepository.getById(matchDTO.getWinner()));
        matchRepository.save(match);
        return "redirect:/";
    }

    @GetMapping("/addwinner")
    public String addWinner(Model model) {
        model.addAttribute("matches", matchRepository.findAll()
                .stream()
                .sorted(Match::compareTo)
                .toList());
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("match", new MatchDTO());
        return "/addwinner";
    }
    @GetMapping("/matches")
    public String showAll(Model model) {
        model.addAttribute("matches", matchRepository.findAll()
                .stream()
                .sorted(Match::compareTo)
                .toList());
        model.addAttribute("teams", teamRepository.findAll());
        return "/matches";
    }
}
