package com.bg.phrobe;

import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminWebApplicationInitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Order(HIGHEST_PRECEDENCE)
public class PhrobeBootApplication extends SpringBootServletInitializer {


    /* Used for running in "embedded" mode */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                LightAdmin.configure(servletContext)
                        .basePackage("com.bg.phrobe.lightadmin")
                        .baseUrl("/lightadmin")
                        .security(false);

                new LightAdminWebApplicationInitializer().onStartup(servletContext);
            }
        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder sAB = new SpringApplicationBuilder(
                PhrobeBootApplication.class
                , SpringSecurityConfigurer.class
        );

        sAB.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PhrobeBootApplication.class);
    }
}