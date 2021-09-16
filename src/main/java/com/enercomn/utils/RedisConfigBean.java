package com.enercomn.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfigBean {

    private String ip;

    private int port;

    private String password;

    private int maxTotal;

    private int maxIdle;

    private long maxWaitMillis;

    private boolean testOnBorrow;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    @Override
    public String toString() {
        return "RedisConfigBean{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", maxWaitMillis=" + maxWaitMillis +
                ", testOnBorrow=" + testOnBorrow +
                '}';
    }
}
