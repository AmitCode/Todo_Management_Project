package com.todo.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize)-> {
//                    authorize.requestMatchers(HttpMethod.POST,"/todoServices/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT,"/todoServices/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/todoServices/**").hasAnyRole("ADMIN","USER");
//
                    authorize.requestMatchers(HttpMethod.GET,"/todoServices/**").permitAll();
                   authorize.anyRequest().authenticated();
                }).httpBasic((Customizer.withDefaults()));

        return httpSecurity.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
@Bean
    public UserDetailsService userDetailsService(){
        UserDetails amit = User.builder()
                .username("AmitKr")
                .password(passwordEncoder().encode("Amti12656"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("ADMIN")
                .password(passwordEncoder().encode("Admin12Adin123#656"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(amit,admin);
    }
}
