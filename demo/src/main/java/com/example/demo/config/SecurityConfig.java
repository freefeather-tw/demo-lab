package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Value("${cors.allowed.origin}")
    private String allowedOrigin;

    @Bean
    SecurityFilterChain securityFilterChain(@Autowired HttpSecurity http) throws Exception {

        http
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .cors();
        //.oauth2Login(oauth2Login ->
        //oauth2Login.loginPage("/oauth2/authorization/articles-client-oidc"))
        //.oauth2Client(withDefaults());

        return http.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(allowedOrigin);
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
        return source;
    }
}
