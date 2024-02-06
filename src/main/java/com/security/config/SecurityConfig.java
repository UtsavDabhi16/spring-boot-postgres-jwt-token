package com.security.config;

import com.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers("/home/admin").hasRole("ADMIN")
                                        .requestMatchers("/auth/**", "/home/public").permitAll()
/*                                        .requestMatchers("/api/users/add").hasRole("ADMIN")
                                        .requestMatchers("/api/users/update").hasAnyRole("ADMIN", "INTERMEDIATE")
                                        .requestMatchers("/api/users/view", "/normal").hasAnyRole("ADMIN", "USER", "INTERMEDIATE")
                                        .requestMatchers("/api/delete/{id}").hasRole("ADMIN")*/
                                        .anyRequest()
                                        .authenticated()
                )
                .sessionManagement(
                        session
                                ->
                                session
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
