package com.prashrajan.taxicabservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class APISecurityConfiguration extends WebSecurityConfigurerAdapter
{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
            .withUser("admin").password("password").roles("ADMIN").and()
            .withUser("user").password("userpassword").roles("USER");
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {

        httpSecurity.httpBasic().and().authorizeRequests()
            .antMatchers("**/v1/**").hasRole("USER")
            .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
            .anyRequest().authenticated().and()
            .csrf().disable().headers().frameOptions().disable();
    }

}
