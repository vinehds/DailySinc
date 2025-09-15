package com.vinehds.dailysinc.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> {})
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, ".auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/teams").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/teams").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/teams").hasRole("TECHLEAD")
                        .requestMatchers(HttpMethod.PUT, "/teams/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/developers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/developers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/developers").hasRole("TECHLEAD")
                        .requestMatchers(HttpMethod.PUT, "/developers/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/dailies").hasRole("TECHLEAD")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
