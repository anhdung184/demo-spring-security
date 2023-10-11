package com.example.api.Security;

import com.example.api.Model.Account;
import com.example.api.Model.JwtTokenProvider;
import com.example.api.Model.MyUserDetails;
import com.example.api.Service.impl.AccountServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    public JwtTokenProvider jwtTokenProvider;
    @Bean
    public JwtTokenProvider  jwtTokenProvider() {
        return new JwtTokenProvider();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AccountServiceImpl accountServiceImpl() {
        return new AccountServiceImpl();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager getAuthenticationManager() throws Exception {
//        // Get AuthenticationManager bean
//        return super.authenticationManager();
//    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(auth.getDefaultUserDetailsService()); // cung cấp password encoder
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accountServiceImpl());
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/dangki").permitAll()
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/books")
                        .successHandler(((request, response, authentication) -> {
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            // Tạo token JWT
                            String token = jwtTokenProvider.generateToken((MyUserDetails) authentication.getPrincipal());

                            // Thêm token JWT vào phản hồii
                            response.addHeader("Authorization", "Bearer " + token);
                        }))
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .deleteCookies("checklogin")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                )
                .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                        .alwaysRemember(true)
                        .tokenValiditySeconds(5184000)
                        .rememberMeCookieName("checklogin")
                        .key("somesecret"));

        return http.build();
    }
}