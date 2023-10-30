package com.example.api.Security;


import com.example.api.Model.JwtTokenProvider;
import com.example.api.Service.impl.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {

    public JwtTokenProvider jwtTokenProvider;
//    @Bean
//    public JwtTokenProvider  jwtTokenProvider() {
//        return new JwtTokenProvider();
//    }
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
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(auth.getDefaultUserDetailsService());// cung cấp password encoder
//        auth
//                .ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                .url("ldap://localhost:8035/dc=springframework,dc=org")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("userPassword");
//    }
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
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/books")
//                        .successHandler(((request, response, authentication) -> {
//                            SecurityContextHolder.getContext().setAuthentication(authentication);
//                            // Tạo token JWT
//                            String token = jwtTokenProvider.generateToken((MyUserDetails) authentication.getPrincipal());
//
//                            // Thêm token JWT vào phản hồi
//                            response.addHeader("Authorization", "Bearer " + token);
//                        }))
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
