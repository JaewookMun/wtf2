package com.wtf2.erp.config.security.form;

import com.wtf2.erp.association.domain.UserGroup;
import com.wtf2.erp.config.security.form.AppUserDetails;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.dto.GroupInfo;
import com.wtf2.erp.user.domain.User;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);

        User user = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user matches the loginId"));

        List<UserGroup> userGroups = user.getUserGroups();

        GroupInfo groupInfo;
        if (userGroups.isEmpty()) groupInfo = null;
        else {
            Group group = userGroups.stream()
                    .filter(g -> g.isActive())
                    .findFirst()
                    .map(userGroup -> userGroup.getGroup())
                    .get();

            groupInfo = new GroupInfo(group.getId(), group.getName());
        }

        return AppUserDetails.builder()
                .username(user.getLoginId())
                .password(user.getPassword())
                .groupInfo(groupInfo)
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().getAuthority())))
                .build();
    }
}
