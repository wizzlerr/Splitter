package com.ootb.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Adam on 2017-01-26.
 */
@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().cacheControl();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("**/*.html").permitAll()
                .antMatchers("**/*.css").permitAll()
                .antMatchers("**/*.js").permitAll()
        ;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(jdbcTemplate.getDataSource())
                .usersByUsernameQuery(
                        "select userName,password, enabled from users where userName=?")
                .authoritiesByUsernameQuery(
                        "select userName, role from user_roles where userName=?");
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender mailSender(){
        return new JavaMailSenderImpl();
    }
}