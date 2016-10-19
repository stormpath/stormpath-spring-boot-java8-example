package com.stormpath.spring.boot.examples;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.servlet.mvc.WebHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebHandlers {

    private static final Logger log = LoggerFactory.getLogger(WebHandlers.class);

    // old school
    @Bean
    public WebHandler registerPreHandler() {
        return new WebHandler() {
            @Override
            public boolean handle(HttpServletRequest request, HttpServletResponse response, Account account) {
                log.info("----> PreRegisterHandler");
                return true;
            }
        };
    }

    // now with lambdas
    @Bean
    public WebHandler registerPostHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Account account) -> {
            log.info("----> PostRegisterHandler");
            return true;
        };
    }

    // even shorter
    @Bean
    public WebHandler loginPreHandler() {
        return (request, response, account) -> {
            log.info("----> PreLoginHandler");
            return true;
        };
    }

    // shortest
    @Bean
    public WebHandler loginPostHandler() {
        return (request, response, account) -> account != null;
    }
}
