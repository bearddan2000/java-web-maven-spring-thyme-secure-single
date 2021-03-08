package example.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {


    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  		http
  			.authorizeRequests()
  				.antMatchers("/login").permitAll()
  				.anyRequest().authenticated()
  				.and()
  			.formLogin()
  				.loginPage("/login")
          .loginProcessingUrl("/login")
          .defaultSuccessUrl("/home", true)
  				.permitAll();
    }

      	@Bean
      	@Override
      	public UserDetailsService userDetailsService() {

          UserDetails userdetails =
      		  User.withDefaultPasswordEncoder()
      				.username("user")
      				.password("pass")
      				.roles("USER")
      				.build();

              return new InMemoryUserDetailsManager(userdetails);
      	}
}
