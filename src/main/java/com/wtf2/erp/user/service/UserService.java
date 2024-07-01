package com.wtf2.erp.user.service;

import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;

    private final PasswordEncoder passwordEncoder;

    public Long register(Long companyId, String userName, String email, String password) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        password = passwordEncoder.encode(password);

        // TODO : 부서 / 사용자 권한 체크
        User newUser = User.builder()
                .company(company)
                .name(userName)
                .loginId(email)
                .password(password)
                .role(Role.GENERAL)
                .build();

        return userRepository.save(newUser).getId();
    }
}
