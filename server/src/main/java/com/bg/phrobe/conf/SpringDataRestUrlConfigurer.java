package com.bg.phrobe.conf;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.URI;

@Configuration
public class SpringDataRestUrlConfigurer extends RepositoryRestMvcConfiguration {


    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBaseUri(URI.create("/dataRest"));
    }

}
