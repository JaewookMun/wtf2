package com.wtf2.erp.user.service;

import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.repository.GroupRepository;
import com.wtf2.erp.user.domain.Role;
import com.wtf2.erp.user.domain.User;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long register(String userName, String email, String password) {
        password = passwordEncoder.encode(password);

        // TODO : 부서 / 사용자 권한 체크
        User newUser = User.builder()
                .name(userName)
                .loginId(email)
                .password(password)
                .role(Role.GENERAL)
                .build();

        return userRepository.save(newUser).getId();
    }
}
