/*----------------------------------------------------------------------------*/
/* Source File:   WEBCLIENTCONFIG.JAVA                                        */
/* Copyright (c), 2025 The Musketeers                                         */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.11/2025  COQ  File created.
 -----------------------------------------------------------------------------*/
package com.themusketeers.jps.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Only used in unit test to complete Spring Boot Context beans.
 *
 * @author COQ - Carlos Adolfo Ortiz Q.
 */
@Configuration
public class WebClientConfig {

    /**
     * Define webClientBuilder bean.
     *
     * @return WebClient.Builder instance.
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}