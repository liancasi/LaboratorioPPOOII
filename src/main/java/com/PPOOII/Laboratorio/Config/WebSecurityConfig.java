package com.PPOOII.Laboratorio.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.PPOOII.Laboratorio.Config.Model.Constans;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf
                        .disable())
                .authorizeHttpRequests( authz -> authz
                		// other configuration options
                		.requestMatchers(Constans.LOGIN_URL).permitAll().requestMatchers(HttpMethod.OPTIONS, "/**")
                		.permitAll()
                		// all other requests need to be authenticated
                		.anyRequest().authenticated())
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
