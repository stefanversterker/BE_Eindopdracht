package nl.novi.eindopdracht.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${client-id}")
    private String clientId;

    @Value("${audience}")
    private String audience;

    @Value("${issuer-uri}")
    private String issuerUri;

    @Bean
    public JwtDecoder jwtDecoder() {

        NimbusJwtDecoder jwtDecoder =
                JwtDecoders.fromIssuerLocation(issuerUri);

        OAuth2TokenValidator<Jwt> audienceValidator =
                new JwtAudienceValidator(audience);

        OAuth2TokenValidator<Jwt> withIssuer =
                JwtValidators.createDefaultWithIssuer(issuerUri);

        OAuth2TokenValidator<Jwt> validator =
                new DelegatingOAuth2TokenValidator<>(
                        withIssuer,
                        audienceValidator);

        jwtDecoder.setJwtValidator(validator);

        return jwtDecoder;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
            throws Exception {

        return http
                .httpBasic(httpBasic ->
                        httpBasic.disable())
                .csrf(csrf ->
                        csrf.disable())
                .cors(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.decoder(jwtDecoder())
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            Map<String, Object> resourceAccess =
                    jwt.getClaim("resource_access");

            if (resourceAccess == null) {
                return List.<GrantedAuthority>of();
            }

            Map<String, Object> client =
                    (Map<String, Object>) resourceAccess.get(clientId);

            if (client == null) {
                return List.<GrantedAuthority>of();
            }

            List<String> roles =
                    (List<String>) client.get("roles");

            if (roles == null) {
                return List.<GrantedAuthority>of();
            }

            Collection<GrantedAuthority> authorities =
                    roles.stream()
                            .map(role -> (GrantedAuthority)
                                    new SimpleGrantedAuthority("ROLE_" + role))
                            .toList();

            return authorities;
        });

        return converter;
    }

}