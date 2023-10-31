package com.project.TasteTreasure.repositories.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.project.TasteTreasure.classes.User;

@Service
public class CustomUserDetailsService extends SavedRequestAwareAuthenticationSuccessHandler
        implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        // if (!user.isEmailVerified()) {
        // throw new EmailNotVerifiedException("Email not verified");
        // }

        Set<GrantedAuthority> authorities = new HashSet<>();
        String roleName = "USER";

        if (roleName != null && !roleName.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority(roleName));
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities);
    }

}
