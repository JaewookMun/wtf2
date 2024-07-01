package com.wtf2.erp.user.domain;

import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.dept.domain.Dept;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public User(Company company, Dept dept, String name, String loginId, String password, Role role) {
        this.company = company;
        this.dept = dept;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }
}
