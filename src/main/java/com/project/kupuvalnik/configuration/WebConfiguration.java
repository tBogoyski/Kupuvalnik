package com.project.kupuvalnik.configuration;

import com.project.kupuvalnik.web.interceptor.InactiveUserInterceptor;
import com.project.kupuvalnik.web.interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final InactiveUserInterceptor inactiveUserInterceptor;
    private final StatsInterceptor statsInterceptor;

    public WebConfiguration(InactiveUserInterceptor inactiveUserInterceptor, StatsInterceptor statsInterceptor) {
        this.inactiveUserInterceptor = inactiveUserInterceptor;
        this.statsInterceptor = statsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inactiveUserInterceptor);
        registry.addInterceptor(statsInterceptor);
    }
}
