package com.geekbrains.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(JwtProperties.PREFIX)
public class JwtProperties {

    public static final String PREFIX = "jwt";

    public String secret;

    public Duration duration;


}
