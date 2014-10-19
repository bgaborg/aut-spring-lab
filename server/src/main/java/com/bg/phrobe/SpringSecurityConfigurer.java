package com.bg.phrobe;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SpringSecurityConfigurer extends WebMvcConfigurerAdapter {

    private static Logger logger = Logger.getLogger(SpringSecurityConfigurer.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        private SecurityProperties security;

        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .antMatchers("/lightadmin/**").hasRole("ADMIN")

                    .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        }

        PasswordEncoder sha256PasswordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                logger.warn(rawPassword);
                return Hashing.sha256().hashString(rawPassword, Charsets.UTF_8).toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                logger.warn("raw: " + rawPassword + " encoded: " + encodedPassword);

                return encodedPassword.equals(Hashing.sha256().hashString(rawPassword, Charsets.UTF_8).toString());
            }
        };

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(this.dataSource)
                    .passwordEncoder(sha256PasswordEncoder);
        }

    }

}