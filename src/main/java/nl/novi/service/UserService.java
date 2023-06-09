package nl.novi.service;

import java.util.List;

import nl.novi.dto.UserRegistrationDto;
import nl.novi.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	User saveAdmin(UserRegistrationDto registrationDto);

	List<User> getAll();
}
