//package com.tesla.demo.douban.configuration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {
//    private final Logger logger = LoggerFactory.getLogger(LoginRequiredInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.info("preHandle: " + request.getRequestURI());
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("Completion: " + request.getRequestURI());
//        super.afterCompletion(request, response, handler, ex);
//    }
//}
