package com.example.peopleflowdemo.employeemanagement.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.security.interfaces.RSAPublicKey;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CoreResourceServerFactory {


    public static JwtDecoder createJwtDecoder(RSAPublicKey key, String iss, AudienceValidator audienceValidator) {

        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(key).build();
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(iss);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }


    public static JwtAuthenticationConverter createJwtAuthenticationConverter(SecurityProps securityProps) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {

            List<String> rolesList = jwt.getClaimAsStringList("roles");
            Collection<GrantedAuthority> authorities = new HashSet<>();
            if (rolesList == null) return authorities;

            for (String jwtRole : rolesList) {
                if (!securityProps.getPrivileges().containsKey(jwtRole)) continue;
                authorities.addAll(securityProps.getPrivileges().get(jwtRole)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
            }
            return authorities;
        });

        return jwtAuthenticationConverter;
    }


}
