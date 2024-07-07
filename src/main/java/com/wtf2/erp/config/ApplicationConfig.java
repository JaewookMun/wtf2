package com.wtf2.erp.config;

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
public class ApplicationConfig {

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
        return new SecurityAuditorAware();
    }
}
