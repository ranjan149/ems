package com.example.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                //.requestMatchers("/login/**").permitAll()
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/employee-list").permitAll()
                                .requestMatchers("/employee-form").permitAll()
                                .requestMatchers("/**").hasRole("USER")
                                .requestMatchers("/ admin/**").hasRole("ADMIN")
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .failureUrl("/login?failed")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/employee-list")
                                .permitAll())
                .logout(logout -> logout.logoutUrl("/logout").permitAll());

        return http. build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
