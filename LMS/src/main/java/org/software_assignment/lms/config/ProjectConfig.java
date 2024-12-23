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
                                                new AntPathRequestMatcher("/api/add/quiz"),
                                                new AntPathRequestMatcher("/api/track/quiz/{quizId}"),
                                                new AntPathRequestMatcher("/api/course/{courseId}/add/mediaFile"),
                                                new AntPathRequestMatcher("/api/lesson/{lessonId}/generate/OTP"),
                                                new AntPathRequestMatcher("/api/course/add/questionBank"),
                                                new AntPathRequestMatcher("/api/assigment/grade/{assignmentID}/{student_id}"),
                                                new AntPathRequestMatcher("/api/add/assigment"),
                                                new AntPathRequestMatcher("/api/view/assigment/{assignmentId}/{studentId}"),
                                                new AntPathRequestMatcher("/api/grade/assigment/{assignmentId}/{studentId}"),
                                                new AntPathRequestMatcher("/api/track/assigment/{assignmentId}"),
                                                new AntPathRequestMatcher("/api/track/lesson/{lessonId}")
                                        ).hasAuthority("INSTRUCTOR")
                                            .requestMatchers(

                                                    new AntPathRequestMatcher("/api/enroll/{studentId}"),
                                                    new AntPathRequestMatcher("/api/view/courses"),
                                                    new AntPathRequestMatcher("/api/submit/quiz/{quizId}"),
                                                    new AntPathRequestMatcher("/api/quiz/{quizId}/feedback"),
                                                    new AntPathRequestMatcher("/api/course/material"),
                                                    new AntPathRequestMatcher("/api/attend/lesson/{studentId}"),
                                                    new AntPathRequestMatcher("/api/course/media"),
                                                    new AntPathRequestMatcher("/api/submit/assigment/{studentId}"),
                                                    new AntPathRequestMatcher("/api/assigment/{assigmentId}/feedback")


                                                    ).hasAuthority("STUDENT")
                                        .requestMatchers(
                                                new AntPathRequestMatcher("/api/view/students/course/")

                                        ).hasAnyAuthority("INSTRUCTOR","ADMIN")
                                        .requestMatchers(
                                                new AntPathRequestMatcher("/api/assigment/{assignmentID}")

                                        )
                                        .hasAnyAuthority("INSTRUCTOR","STUDENT")
                                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)

        ; // Add custom filter after the CSRF filter
        return http.build(); // Build the SecurityFilterChain
    }
}
