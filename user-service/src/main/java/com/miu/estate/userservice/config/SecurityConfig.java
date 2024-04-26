package com.miu.estate.userservice.config;

import com.miu.estate.userservice.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthFilter authFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        //TODO: Add more endpoints
                        .requestMatchers("/api/v1/health/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/properties/all").permitAll()
                        .requestMatchers("/api/v1/properties/{id}").permitAll()
                        .requestMatchers("/api/v1/properties/search?*").permitAll()
                        .requestMatchers("/api/v1/users/all").permitAll()
                        .requestMatchers("/api/v1/users/detail").permitAll()
                        .requestMatchers("/api/v1/agents/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.AGENT.name(), UserRole.USER.name())
                        .requestMatchers("/api/v1/favorite-properties/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.AGENT.name(), UserRole.USER.name())
                        .requestMatchers("/api/v1/images/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.AGENT.name())
                        .requestMatchers("/api/v1/properties/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.AGENT.name(), UserRole.USER.name())
                        .requestMatchers("/api/v1/ratings/**").hasAnyRole(UserRole.USER.name())
                        .requestMatchers("/api/v1/users/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.AGENT.name(), UserRole.USER.name())
                        .requestMatchers("/api/v1/admin/***").hasAnyRole(UserRole.ADMIN.name())


//                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
//                .httpBasic(Customizer.withDefaults())
                .httpBasic().disable()
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> response.sendError(401, "Unauthorized")));
        //TODO: Remove CSRF, CORS
        return http.build();
    }
}
