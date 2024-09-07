package com.wtf2.erp.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class ApplicationConfig {

    @PersistenceContext
    private final EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
        xmlHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_XML));

        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        converters.add(xmlHttpMessageConverter);

        return restTemplate;
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new JpaSecurityAuditorAware();
    }
}
