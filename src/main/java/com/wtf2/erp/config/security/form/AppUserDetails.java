package com.wtf2.erp.config.security.form;

import com.wtf2.erp.group.domain.Group;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Spring Security 기능을 활용하기 위해 사용하는 클래스.
 * DB에 저장되어있는 사용자 정보를 in-memory 상에 로드시킨다.
 * accountNonExpired, accountNonLocked, credentialsNonExpired, enabled 의 getter 메서드는
 * 기능의 필요에 따라 구현
 */
@Getter
public class AppUserDetails extends DefaultOAuth2User implements UserDetails {

    /**
     * loginId : email format string
     */
    private final String username;
    private String password;
    /**
     * 불필요한 SELECT 쿼리를 방지하기 위해 Entity 인스턴스를 필드로 주입.
     */
    private Group group;
    private boolean isOAuthUser;
//    private Set<GrantedAuthority> authorities;
    private static final String FORM_ATTRIBUTE_KEY = "form";
    private static final Map<String, Object> formAttributes = Map.of(FORM_ATTRIBUTE_KEY, "default");

    // TODO : Group 제거 (entity를 저장하고 있는 것은 바람직하지 않아보임)
    /**
     * Constructor for form login
     *
     * @param username
     * @param password
     * @param group
     * @param authorities
     */
    @Builder
    public AppUserDetails(String username, String password, Group group, Collection<? extends GrantedAuthority> authorities) {
        super(authorities, formAttributes, FORM_ATTRIBUTE_KEY);

        this.username = username;
        this.password = password;
        this.group = group;
//        this.authorities = Collections.unmodifiableSet(authoritiesSetFor(authorities));
    }

    /**
     * Constructor for OAuth login
     *
     * @param authorities
     * @param attributes
     * @param nameAttributeKey
     * @param username
     */
    public AppUserDetails(String username, Map<String, Object> attributes, String nameAttributeKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities, attributes, nameAttributeKey);
        this.username = username;
        isOAuthUser = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * custom method which is derived from {org.springframework.security.core.userdetails.UserDetails.sortAuthorities()}
     */
    private static Set<GrantedAuthority> authoritiesSetFor(Collection<? extends GrantedAuthority> authorites) {
        Assert.notNull(authorites, "Cannot pass a null GrantedAuthority collection");

        // TreeSet의 경우 내부의 순서를 보장해줘야한다.
        // Set<GrantedAuthority> authoritiesSet = new TreeSet<>();
        Set<GrantedAuthority> authoritiesSet = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorites) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            authoritiesSet.add(grantedAuthority);
        }

        return authoritiesSet;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User user) {
            return this.username.equals(user.getUsername());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}