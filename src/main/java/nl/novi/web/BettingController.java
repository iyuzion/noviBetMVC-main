package nl.novi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BettingController {
    @GetMapping("/bet")
    public String login() {
        return "bet";
    }
}
