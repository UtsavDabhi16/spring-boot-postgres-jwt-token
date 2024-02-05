package com.security;

import com.security.filter.JwtAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterFilterRegistrationBean() {
        final FilterRegistrationBean<JwtAuthenticationFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JwtAuthenticationFilter());
        filterFilterRegistrationBean.setOrder(-1);
        filterFilterRegistrationBean.setName("JWT Auth Filter");
        filterFilterRegistrationBean.setUrlPatterns(List.of("/test/**","/public/**"));
        return filterFilterRegistrationBean;
    }

}
