package com.example.springrest.configs;

import com.example.springrest.entities.ResponseObject;
import com.example.springrest.services.UserDetailsServiceImpl;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        .antMatchers(HttpMethod.POST, "/artists/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/artists/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.POST, "/songs/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/songs/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.POST, "/album/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/album/**").hasAuthority("ADMIN")
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().defaultSuccessUrl("/user/me")
        .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedHandler(new AccessDeniedExceptionHandler())
        .and()
        .httpBasic();
    http.cors().disable().csrf().disable();
    ;
  }

  public class AccessDeniedExceptionHandler implements AccessDeniedHandler {
    Gson gson = new Gson();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException, ServletException {
      writeCustomResponse(response);
    }

    private void writeCustomResponse(HttpServletResponse response) {
      if (!response.isCommitted()) {
        try {
          ResponseObject responseObject = new ResponseObject(HttpStatus.FORBIDDEN.value(), "User is not authorized.", null);
          String responseString = gson.toJson(responseObject);
          PrintWriter out = response.getWriter();
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          out.print(responseString);
          out.flush();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}