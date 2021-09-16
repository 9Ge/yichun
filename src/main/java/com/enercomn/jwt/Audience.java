package com.enercomn.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "audience")
@PropertySource("classpath:jwt.properties")
public class Audience {
    private String base64Secret;
    private int expiresSecond;
    private String tokenType;

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
