package org.software_assignment.lms.config;

import org.software_assignment.lms.filters.CsrfLoggerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class); // Add custom filter after the CSRF filter

        // Uncomment if you want to configure a custom CSRF token repository
        // http.csrf(csrf -> csrf.csrfTokenRepository());

        return http.build(); // Build the SecurityFilterChain
    }
}
