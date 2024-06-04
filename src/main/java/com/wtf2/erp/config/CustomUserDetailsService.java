package com.wtf2.erp.config;

import com.wtf2.erp.user.domain.User;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByLoginId(username);

        if(user.isPresent())
            return new org.springframework.security.core.userdetails
                    .User(user.get().getLoginId(), user.get().getPassword(), List.of(new SimpleGrantedAuthority("USER")));
        else
            throw new IllegalArgumentException("There is no user matches the loginId");
    }
}
