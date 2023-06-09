package nl.novi.config;

import nl.novi.dto.UserRegistrationDto;
import nl.novi.model.Team;
import nl.novi.repository.TeamRepository;
import nl.novi.service.UserService;
import nl.novi.web.TeamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConstruct {
    @Autowired
    private UserService userService;
    private TeamRepository teamRepository;

    @javax.annotation.PostConstruct
    public void init() {
        userService.save(UserRegistrationDto.builder()
                .email("Hannah")
                .firstName("Hannah")
                .lastName("Hannah")
                .password("Hannah")
                .build());

        userService.saveAdmin(UserRegistrationDto.builder()
                .email("admin")
                .firstName("admin")
                .lastName("admin")
                .password("admin")
                .build());



    }

}
