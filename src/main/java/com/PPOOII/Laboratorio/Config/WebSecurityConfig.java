package com.PPOOII.Laboratorio.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.PPOOII.Laboratorio.Config.Model.Constans;

@EnableWebSecurity
@Configuration
class WebSecurityConfig implements WebMvcConfigurer{

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;
    

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf
                        .disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(Constans.LOGIN_URL, "/apiKey", "/Laboratorio1/persona").permitAll() // Permitir el acceso a /apiKey
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir todas las rutas
                .allowedOrigins("http://localhost:3000") // Permitir este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*"); // Permitir todos los headers
    }
    
}
