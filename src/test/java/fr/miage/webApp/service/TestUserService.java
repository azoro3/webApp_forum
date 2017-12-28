package fr.miage.webApp.service;

import fr.miage.webApp.model.User;
import fr.miage.webApp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        User user = new User();
        user.setUsername("user123");
        user.setEmail("user123@mail.com");
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
    }

    @Test
    public void testFindByUsername() {
        String name = "user123";
        User found = userService.findByUsername(name);
        assertEquals("user123", found.getUsername());
        assertEquals("user123@mail.com", found.getEmail());
    }

}
