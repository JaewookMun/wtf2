package com.wtf2.erp.config.security.oauth;

import com.wtf2.erp.association.domain.UserGroup;
import com.wtf2.erp.config.security.form.AppUserDetails;
import com.wtf2.erp.group.domain.Group;
import com.wtf2.erp.group.dto.GroupInfo;
import com.wtf2.erp.user.domain.Role;
import com.wtf2.erp.user.domain.User;
import com.wtf2.erp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId=userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName=userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes receivedUser = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        return saveOrUpdate(receivedUser);
    }

    private AppUserDetails saveOrUpdate(OAuthAttributes oAuthUser) {
        User user =
                userRepository.findByLoginId(oAuthUser.getEmail())
                .orElse(
                        User.builder()
                                .role(Role.TEMP)
                                .loginId(oAuthUser.getEmail())
                                .build()
                );

        user.updateInfo(oAuthUser.getName(), oAuthUser.getMobile());
        userRepository.save(user);
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

        return new AppUserDetails(
                user.getLoginId(),
                groupInfo,
                oAuthUser.getAttributes(),
                oAuthUser.getNameAttributeKey(),
                List.of(new SimpleGrantedAuthority(user.getRole().getAuthority()))
        );

    }
}
