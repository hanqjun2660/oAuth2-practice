package com.sungjun.formoauthpractice.Config;

import com.sungjun.formoauthpractice.Service.PrincipalOauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PrincipalOauthUserService principalOauthUserService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        /*.requestMatchers("/member/**").authenticated()*/
                        .requestMatchers("/dashboard").hasAuthority("USER")
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .usernameParameter("userid")
                        .passwordParameter("userpw")
                        .defaultSuccessUrl("/dashboard")
                )
                .oauth2Login(login -> login
                        .loginPage("/")
                        .defaultSuccessUrl("/dashboard")
                        .userInfoEndpoint()
                        .userService(principalOauthUserService)
                );

        return http.build();
    }
}
