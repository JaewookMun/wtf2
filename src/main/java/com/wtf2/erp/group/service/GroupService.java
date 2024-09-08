package com.wtf2.erp.group.service;

import com.wtf2.erp.association.domain.UserGroup;
import com.wtf2.erp.dept.domain.Dept;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.dto.GroupInfo;
import com.wtf2.erp.group.repository.GroupRepository;
import com.wtf2.erp.user.domain.User;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
    public GroupInfo search(String code) {

        return groupRepository.findByCode(code)
                .map(g -> new GroupInfo(g.getId(), g.getName()))
                .orElseThrow(() -> new IllegalArgumentException("The group code [" + code + "] is not validate."));
    }

    @Transactional
    public Long register(String groupName, String loginId) {
        User user = userRepository.findByLoginId(loginId).get();

        // TODO: make logic for GUID
        String guid = UUID.randomUUID() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyyyMMddHHmmss"));

        Group group = new Group(groupName, guid);
        group.addDept(new Dept(groupName, null));
        user.addGroup(new UserGroup(group));
        groupRepository.save(group);

        return group.getId();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow();
    }
}
