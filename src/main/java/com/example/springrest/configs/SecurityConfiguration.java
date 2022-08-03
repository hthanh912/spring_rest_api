package com.example.springrest.configs;

import com.example.springrest.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//public class SecurityConfiguration {
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//        .authorizeHttpRequests((authz) -> authz
//            //.anyRequest().authenticated()
//            .antMatchers("/user").hasRole("USER")
//            .antMatchers("/home").permitAll()
//        )
//        .httpBasic();
//    return http.build();
//  }
//
////  @Bean
////  public UserDetailsService users() {
////    // The builder will ensure the passwords are encoded before saving in memory
////    User.UserBuilder users = User.withDefaultPasswordEncoder();
////    UserDetails user = users
////        .username("user")
////        .password("password")
////        .roles("USER")
////        .build();
////    UserDetails admin = users
////        .username("admin")
////        .password("password")
////        .roles("USER", "ADMIN")
////        .build();
////    return new InMemoryUserDetailsManager(user, admin);
////  }
//
//  @Bean
//  public UserDetailsService userDetailsService() {
//    return new UserDetailsServiceImpl();
//  }
//
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public DaoAuthenticationProvider authenticationProvider() {
//    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService());
//    authProvider.setPasswordEncoder(passwordEncoder());
//
//    return authProvider;
//  }
//
////  @Override
////  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////    auth.authenticationProvider(authenticationProvider());
////  }
//
////  @Override
////  protected void configure(HttpSecurity http) throws Exception {
////    http.authorizeRequests()
////        .anyRequest().authenticated()
////        .and()
////        .formLogin().permitAll()
////        .and()
////        .logout().permitAll();
////  }
//
//}

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/user").hasAuthority("USER")
        .antMatchers("/**").permitAll()

//        .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//        .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//        .antMatchers("/delete/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        .and()
        .httpBasic();
    http.cors().disable().csrf().disable();
    ;
  }
}