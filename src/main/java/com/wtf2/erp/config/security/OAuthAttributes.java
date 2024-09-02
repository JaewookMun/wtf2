package com.wtf2.erp.config.security;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import static com.wtf2.erp.config.security.Registration.NAVER;

@Getter
public class OAuthAttributes {

    private String name;
    private String email;
    private String mobile;

    /**
     * 일종의 attributes의 식별값으로 보면 될것으로 보이며 <br>
     * 관련하여 DefaultOAuth2User.class 참고
     *
     */
    private String nameAttributeKey;
    private Map<String, Object> attributes;

    @Builder
    public OAuthAttributes(String name, String email, String mobile, String nameAttributeKey, Map<String, Object> attributes) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.nameAttributeKey = nameAttributeKey;
        this.attributes = attributes;
    }

    public static OAuthAttributes of(String registrationId, String nameAttributeKey, Map<String, Object> attributes) {
        if (NAVER.toString().equalsIgnoreCase(registrationId)) return ofNaver("id", nameAttributeKey, attributes);

        return ofOthers(nameAttributeKey, attributes);
    }

    private static OAuthAttributes ofNaver(String nameAttributeKey, String responseKey, Map<String, Object> attributes) {
        attributes = (Map<String, Object>) attributes.get(responseKey);

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .mobile((String) attributes.get("mobile"))
                .nameAttributeKey(nameAttributeKey)
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofOthers(String nameAttributeKey, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .mobile((String) attributes.get("mobile"))
                .nameAttributeKey(nameAttributeKey)
                .attributes(attributes)
                .build();
    }
}
