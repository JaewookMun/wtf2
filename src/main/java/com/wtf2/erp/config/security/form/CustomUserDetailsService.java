package com.wtf2.erp.config.security.form;

import com.wtf2.erp.config.security.form.AppUserDetails;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);

        return userRepository.findByLoginId(username)
                .map(user -> AppUserDetails.builder()
                        .username(user.getLoginId())
                        .password(user.getPassword())
                        .group(user.getGroup())
                        .authorities(List.of(new SimpleGrantedAuthority(user.getRole().getAuthority())))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("There is no user matches the loginId"));
    }
}
