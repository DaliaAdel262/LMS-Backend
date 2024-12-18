package org.software_assignment.lms.config;

import org.software_assignment.lms.filters.CsrfLoggerFilter;
import org.software_assignment.lms.filters.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests.requestMatchers(
                                        "/api/auth/register",
                                        "/api/auth/delete/*"
                                ).hasAuthority("ADMIN")
                                        .requestMatchers(
                                                new AntPathRequestMatcher("/api/add/course"),
                                                new AntPathRequestMatcher("/api/delete/std/course/{courseID}/{stdID}"),
                                                new AntPathRequestMatcher("/api/add/quiz")
                                        ).hasAuthority("INSTRUCTOR")
                                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)

        ; // Add custom filter after the CSRF filter
        return http.build(); // Build the SecurityFilterChain
    }
}
