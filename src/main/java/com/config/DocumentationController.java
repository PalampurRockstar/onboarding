package com.config;


import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//@Component
//@Primary
//@EnableAutoConfiguration
//public class DocumentationController implements SwaggerResourcesProvider {
//    @Override
//    public List get() {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName("pet-onboarding");
//        swaggerResource.setLocation("v3/api-docs");
//        swaggerResource.setSwaggerVersion("3.0");
//        return Collections.singletonList(swaggerResource);
//    }
//}