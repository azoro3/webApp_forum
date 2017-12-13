package fr.miage.webApp.service;

import fr.miage.webApp.model.User;
import fr.miage.webApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        userRepository.save(user);
    }
}
