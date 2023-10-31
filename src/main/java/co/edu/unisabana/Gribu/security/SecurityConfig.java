package co.edu.unisabana.Gribu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    /*
    http.csrf().disable()
        .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .anyRequest().authenticated();
     */
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").permitAll();
  }
}
//documentar la API con swagger
//No se documento a menos de que sea una libreria o una bibloteca,
//Sino es una libreria/bibloteca y hay que documentarlo es porque quedo mal hecho.