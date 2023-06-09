package nl.novi.web;

import nl.novi.model.Bet;
import nl.novi.model.Match;
import nl.novi.model.User;
import nl.novi.model.dto.BetDTO;
import nl.novi.repository.BetRepository;
import nl.novi.repository.MatchRepository;
import nl.novi.repository.TeamRepository;
import nl.novi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

import static java.util.Comparator.reverseOrder;

@Controller
public class BettingController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private BetRepository betRepository;


    @PostMapping("/bet")
    public String bet(@ModelAttribute("match") BetDTO bet) {
        betRepository.save(Bet.builder()
                .user(userRepository.findByEmail(bet.getUser()))
                .match(matchRepository.getById(bet.getMatch()))
                .winner(bet.getWinner())
                .build());
        return "redirect:/";
    }

    @GetMapping("/bet")
    public String bet(Model model) {
        model.addAttribute("matches", matchRepository.findAll()
                .stream()
                .filter(value -> Objects.equals(value.getWinnerName(), "geen winner"))
                .filter(value -> !getBettedMatchesByUser(getUserByContext()).contains(value))
                .sorted(reverseOrder())
                .toList());
        model.addAttribute("bet", new BetDTO());
        return "/bet";
    }

    private User getUserByContext() {
        return userRepository.findByEmail(((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername());
    }

    private List<Match> getBettedMatchesByUser(User user) {
        return betRepository.findAll()
                .stream()
                .filter(value -> value.getUser().equals(user))
                .map(Bet::getMatch)
                .toList();
    }

    @ModelAttribute("juistebets")
    public long juisteBets() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return betRepository.findAll().stream()
                .filter(v -> v.getUser().getEmail().equals(userDetails.getUsername()))
                .filter(Bet::correctVoorspelt)
                .count();
    }
}
