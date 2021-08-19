package com.example.peopleflowdemo.employeemanagement.security;

import com.example.peopleflowdemo.employeemanagement.dto.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.security.interfaces.RSAPublicKey;

import static com.example.peopleflowdemo.employeemanagement.security.CoreResourceServerFactory.createJwtAuthenticationConverter;


@RequiredArgsConstructor
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry httpInAction = http
                .csrf().disable()
                .authorizeRequests();

        for (SecurityProps.ResourceMatcher resourceMatcher : securityProps().getResourceMatchers()) {
            httpInAction = httpInAction
                    .mvcMatchers(HttpMethod.valueOf(resourceMatcher.getMethod()), resourceMatcher.getPath())
                    .access(resourceMatcher.getAccess());
        }

        httpInAction
                .anyRequest().denyAll()
                .and().oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(createJwtAuthenticationConverter(securityProps()))
                .and()
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setStatus(403);
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    httpServletResponse.getWriter().write(objectMapper.writeValueAsString(CommonResponse.builder().devMessage("access_denied").build()));
                });


    }


    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer webInAction = web
                .ignoring();
        for (SecurityProps.ResourceIgnoreMather resourceIgnoreMather : securityProps().getResourceIgnoring()) {
            webInAction.mvcMatchers(HttpMethod.valueOf(resourceIgnoreMather.getMethod()), resourceIgnoreMather.getPath());
        }
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        return CoreResourceServerFactory.createJwtDecoder(this.key, this.iss, new AudienceValidator(this.resourceId));
    }


    @Bean(name = "securityProps")
    @ConfigurationProperties(prefix = "security-props")
    public SecurityProps securityProps() {
        return new SecurityProps();
    }


    @Value("${spring.security.oauth2.resourceserver.jwt.resource-id}")
    private String resourceId;

    @Value("${spring.security.oauth2.resourceserver.jwt.iss}")
    private String iss;

    @Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}")
    private RSAPublicKey key;


    private final ObjectMapper objectMapper;

}