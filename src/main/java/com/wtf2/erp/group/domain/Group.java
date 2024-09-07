package com.wtf2.erp.group.domain;

import com.wtf2.erp.dept.domain.Dept;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "groups")
public class Group {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String guid;

    /**
     * 사용자를 초대할 때, 특정 기간동안만 유효한 group code값 생성
     */
    @Column(unique = true)
    private String code;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Dept> dept = new ArrayList<>();

    public Group(String name, String guid) {
        this.name = name;
        this.guid = guid;
    }

    public void addDept(Dept dept) {
        this.dept.add(dept);
        dept.setGroup(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return getId().equals(group.getId()) && Objects.equals(getName(), group.getName()) && getGuid().equals(group.getGuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGuid());
    }
}
