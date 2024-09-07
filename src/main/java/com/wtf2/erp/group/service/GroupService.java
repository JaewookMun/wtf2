package com.wtf2.erp.group.service;

import com.wtf2.erp.dept.domain.Dept;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.dto.GroupDto;
import com.wtf2.erp.group.repository.GroupRepository;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    /**
     * @param code
     * @return
     */
    public GroupDto search(String code) {

        return groupRepository.findByCode(code)
                .map(g -> new GroupDto(g.getId(), g.getName(), g.getGuid()))
                .orElseThrow(() -> new IllegalArgumentException("The group code [" + code + "] is not validate."));
    }

    @Transactional
    public Long register(String groupName, String loginId) {
//        groupRepository.findByGuid(guid).ifPresent(c -> {
//            throw new DataIntegrityViolationException("GUID is duplicate - guid is Unique key constraint");
//        });
        userRepository.findByLoginId(loginId);

        // TODO: make logic for GUID
        String tempGuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss_")) + new Random().nextInt(100);

        Group group = new Group(groupName, tempGuid);
        group.addDept(new Dept(groupName, null));

        return groupRepository.save(group).getId();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow();
    }
}
