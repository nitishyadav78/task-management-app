package com.dogratechnologies.task_management_app.Service;

import com.dogratechnologies.task_management_app.EntityPackage.User;
import com.dogratechnologies.task_management_app.RepsitoryPackage.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not found: " + username));

        String role = (user.getRole() != null && !user.getRole().isEmpty())
                ? user.getRole()
                : "ROLE_USER";
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().replace("ROLE_", ""))
                .build();
    }

    public User registerUser(String username, String rawPassword)
    {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .role("ROLE_USER")
                .build();
        return userRepo.save(user);
    }

}
