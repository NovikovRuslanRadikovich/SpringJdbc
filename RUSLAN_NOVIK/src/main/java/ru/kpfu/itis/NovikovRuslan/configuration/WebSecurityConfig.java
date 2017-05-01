package ru.kpfu.itis.NovikovRuslan.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.kpfu.itis.NovikovRuslan.authentication.AuthProviderImpl;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ru.kpfu.itis.NovikovRuslan"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthProviderImpl authProvider;

    public void configure(AuthenticationManagerBuilder auth){auth.authenticationProvider(authProvider);}

    protected void configure(HttpSecurity http) throws Exception{
        http

                .authorizeRequests()
                .antMatchers("/", "/resources/**","/login","/registration").permitAll()
                .antMatchers("/admin","/admin/new_doctor","/admin/delete_city/**","/admin/delete_polyclinic/**","/admin/delete_doctor/**").hasRole("ADMIN")
                .antMatchers("/follow/**").hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll();
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("login_username").passwordParameter("login_password")
                .permitAll();

        http
                .logout()
                .permitAll()
                .invalidateHttpSession(true);
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
        ;
    }
}
