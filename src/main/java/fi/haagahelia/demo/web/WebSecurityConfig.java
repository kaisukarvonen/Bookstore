package fi.haagahelia.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import fi.haagahelia.demo.services.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailImpl;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        	.antMatchers("/css/**").permitAll() // Enable css when logged out
        	.antMatchers("/books").permitAll()
        	.antMatchers("/booklist").permitAll()
        	.antMatchers("/addBook").permitAll()
        	.antMatchers("/delete/**").permitAll()
        	.anyRequest().authenticated()
        	.and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/booklist")
          .permitAll()
          .and()
      .csrf().disable()
      .logout()
          .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");*/
    	
    	auth.userDetailsService(userDetailImpl).passwordEncoder(new BCryptPasswordEncoder());
    }
}