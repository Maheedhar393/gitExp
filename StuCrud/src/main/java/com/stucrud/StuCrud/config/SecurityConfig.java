package com.stucrud.StuCrud.config;


import com.stucrud.StuCrud.entity.Permissions;
import com.stucrud.StuCrud.filters.JWTAuthFilter;
import com.stucrud.StuCrud.repo.UserDetailsRepository;
import com.stucrud.StuCrud.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/insert","/authenticate").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/student/**").hasAuthority(Permissions.STUDENT_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/student/**").hasAuthority(Permissions.STUDENT_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/student/**").hasAuthority(Permissions.STUDENT_DELETE.name())
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable);
                //.httpBasic(withDefaults());

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
       //Here we are adding our custom filter before the UsernamePasswordAuthenticationFilter
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return  new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }
}
