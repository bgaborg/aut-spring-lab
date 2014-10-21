package com.bg.phrobe;

import com.bg.phrobe.conf.LightAdminConfigurer;
import com.bg.phrobe.conf.SpringSecurityConfigurer;
import com.bg.phrobe.conf.SwaggerConfigurer;
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

    public static void main(String[] args) throws Exception {
        SpringApplicationBuilder sAB = new SpringApplicationBuilder(PhrobeBootApplication.class);

        sAB.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(
                PhrobeBootApplication.class
        );
    }
}