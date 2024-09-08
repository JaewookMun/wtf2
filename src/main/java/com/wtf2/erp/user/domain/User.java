package com.wtf2.erp.user.domain;

import com.wtf2.erp.association.domain.UserGroup;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.dept.domain.Dept;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`USER`")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    /** email */
    @Column(unique = true)
    private String loginId;
    private String password;

    private String phone;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Dept dept;

    /**
     * cascade(remove), orphanRemoval option 모두 적용하면 안됨!!
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    private List<UserGroup> userGroups = new ArrayList<>();

    @Builder
    public User(Dept dept, String name, String loginId, String password, Role role) {
        this.dept = dept;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

    public void updateInfo(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public void addGroup(UserGroup userGroup) {
        if(userGroups.isEmpty()) userGroup.activate();
        userGroups.add(userGroup);
        userGroup.setUser(this);
    }
}
