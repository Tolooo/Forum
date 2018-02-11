package com.example.Forum.services;

import com.example.Forum.models.UserCredentials;
import com.example.Forum.models.UserRole;
import com.example.Forum.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCredentialsService implements UserDetailsService {

    private UserCredentialsRepository userRepository;

    @Autowired
    public UserCredentialsService(UserCredentialsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserCredentials user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("{username.not_found}a '" + username + "'.");
        }
        List<GrantedAuthority> authorities =
                new ArrayList<GrantedAuthority>();
        for (UserRole userRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
