package com.bg.phrobe.conf;

import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class LightAdminConfigurer {
    /* Used for running in "embedded" mode */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                LightAdmin.configure(servletContext)
                        .basePackage("com.bg.phrobe.lightadmin")
                        .baseUrl("/lightadmin")
                        .securityLogoutUrl("/logout")
                        .baseUrl("/");

                new LightAdminWebApplicationInitializer().onStartup(servletContext);
            }
        };
    }
}
