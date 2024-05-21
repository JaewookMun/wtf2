package com.wtf2.erp.user.service;

import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;



}
