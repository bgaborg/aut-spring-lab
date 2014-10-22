package com.bg.phrobe;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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