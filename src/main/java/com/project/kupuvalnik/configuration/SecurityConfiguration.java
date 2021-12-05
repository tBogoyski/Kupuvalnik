package com.project.kupuvalnik.configuration;

import com.project.kupuvalnik.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Autowired
    private KupuvalnikMethodSecurityExpressionHandler kupuvalnikMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return kupuvalnikMethodSecurityExpressionHandler;
    }

    @Bean
    public KupuvalnikMethodSecurityExpressionHandler createExpressionHandler(OfferService offerService) {
        return new KupuvalnikMethodSecurityExpressionHandler(offerService);
    }
}
