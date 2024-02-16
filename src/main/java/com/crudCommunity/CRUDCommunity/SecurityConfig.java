package com.crudCommunity.CRUDCommunity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(
            (authorizeHttpRequests) ->
                authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/edit/{postId}")).authenticated()
                .anyRequest().permitAll()).csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
