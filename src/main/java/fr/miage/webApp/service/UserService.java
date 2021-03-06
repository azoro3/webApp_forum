package fr.miage.webApp.service;

import fr.miage.webApp.model.Role;
import fr.miage.webApp.model.User;
import fr.miage.webApp.repository.RoleRepository;
import fr.miage.webApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Method to save an user in database
     *
     * @param user User to save
     */
    public void saveUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(createRoleIfNotFound("ROLE_USER"));
        userRepository.save(user);
    }

    /**
     * Method to add a role to an user
     *
     * @param name Name of the role
     * @return A set of roles
     */
    private Set<Role> createRoleIfNotFound(String name) {
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        roles.add(role);
        return roles;
    }

    /**
     * Find an user by his username
     *
     * @param username Username of the user to find
     * @return User
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find an user by his email
     *
     * @param email Email of the user to find
     * @return User
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
