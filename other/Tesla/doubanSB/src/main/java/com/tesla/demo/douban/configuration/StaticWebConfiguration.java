//package com.tesla.demo.douban.configuration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class StaticWebConfiguration implements WebMvcConfigurer {
//    private final Logger logger = LoggerFactory.getLogger(StaticWebConfiguration.class);
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        logger.info("add Interceptor: ");
//
//        registry.addInterceptor((HandlerInterceptor) new LoginRequiredInterceptor());
//    }
//}
