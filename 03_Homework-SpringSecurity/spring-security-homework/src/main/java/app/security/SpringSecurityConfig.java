package app.security;

import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/", "/users/register", "/css/*", "/js/*").permitAll()
                .antMatchers("/viruses/show", "/viruses/add", "/viruses/edit/**").access("hasRole('CHEMIST') or hasRole('ADMIN')")
                .antMatchers("/cures/**").access("hasRole('MEDIC') or hasRole('ADMIN')")
                .antMatchers("/viruses/delete/**").access("hasRole('ADMIN')")
                .antMatchers("/users/all").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()//login
                .formLogin().loginPage("/users/login").permitAll().usernameParameter("username").passwordParameter("password")
                .and()//logout
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/users/logout")).logoutSuccessUrl("/")
                .and().//remember me cookie
                 rememberMe().rememberMeParameter("remember").key("!#^%$").rememberMeCookieName("rememberMe").tokenValiditySeconds(1111111)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(this.userService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
