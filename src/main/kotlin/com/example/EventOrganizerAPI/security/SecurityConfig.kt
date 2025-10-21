package com.example.EventOrganizerAPI.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } 
            
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/api/events/**").permitAll()
                    
                    .requestMatchers("/api/tickets/**").permitAll() 
                    
                    .anyRequest().authenticated()
            }
            
            .formLogin { it.disable() }
            .logout { it.disable() }

        return http.build()
    }
}