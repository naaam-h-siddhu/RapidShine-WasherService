package com.rapidshine.carwash.washerservice.config;

import com.rapidshine.carwash.washerservice.service.TokenManager;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig  {

    @Bean
    public RequestInterceptor requestInterceptor(TokenManager tokenManager){
        return new FeignClientInterceptor(tokenManager);
    }

//    @Override
//    public void apply(RequestTemplate template) {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//
//        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
//            HttpServletRequest request = servletRequestAttributes.getRequest();
//            String authHeader = request.getHeader("Authorization");
//
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                template.header("Authorization", authHeader);
//            }
//        }
//    }
}
