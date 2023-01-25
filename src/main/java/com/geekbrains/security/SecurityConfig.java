package com.geekbrains.security;

import com.geekbrains.security.SimpleAuthenticationProvider;
import com.geekbrains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private UserService userService;

    private SecurityFilter securityFilter;

    @Autowired
    public void setSecurityFilter(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return webSecurity -> webSecurity.ignoring().requestMatchers("/auth/**");
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/v1/products/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .requestMatchers("/api/v1/cart/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .requestMatchers("/users/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .requestMatchers("/**").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/auth")
//                .defaultSuccessUrl("/products",true)
//                .permitAll()
                .and()
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(getPasswordEncoder());
//        provider.setUserDetailsService(userService);
//        return provider;
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider... providers) {
        return new ProviderManager(providers);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new SimpleAuthenticationProvider();
    }

}
