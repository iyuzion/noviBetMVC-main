package nl.novi.web;

import nl.novi.model.Bet;
import nl.novi.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	private BetRepository betRepository;

	@GetMapping("/login")
	public String login() {return "login";}

	@GetMapping("/")
	public String home(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("juistebets", betRepository.findAll().stream()
				.filter(v -> v.getUser().getEmail().equals(userDetails.getUsername()))
				.filter(Bet::correctVoorspelt)
				.count());
		return "index";
	}
}
