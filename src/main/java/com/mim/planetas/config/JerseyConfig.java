package com.mim.planetas.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.wadl.internal.WadlResource;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        register(RequestContextFilter.class);
        register(JacksonObjectMapperConfig.class);

//        register(Clima.class);
//        register(Version.class);
        
        packages("com.mim.planetas.rest");
         
        http://localhost:8080/application.wadl
        register(WadlResource.class);
    }
}