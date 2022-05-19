package com.huweiv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author HUWEIV
 * @version 1.0.0
 * @ClassName JackJsonConfig
 * @Description TODO
 * @CreateTime 2022/4/23 9:21
 */

@Configuration
public class JackJsonConfig {

    @Bean("jackJson")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
