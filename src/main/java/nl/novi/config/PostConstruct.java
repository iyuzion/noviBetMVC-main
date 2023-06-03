package nl.novi.config;

import nl.novi.dto.UserRegistrationDto;
import nl.novi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConstruct {
    @Autowired
    private UserService userService;

    @javax.annotation.PostConstruct
    public void init() {
        userService.save(UserRegistrationDto.builder()
                .email("Hannah")
                .firstName("Hannah")
                .lastName("Hannah")
                .password("Hannah")
                .build());
    }

}
