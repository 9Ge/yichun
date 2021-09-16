package com.enercomn.config;


import com.enercomn.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig{

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//ms
        factory.setConnectTimeout(15000);//ms
        return factory;
    }

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate(){
        return restTemplateBuilder.basicAuthentication(Constants.GET_MQTT_CLIENTS_USER,Constants.GET_MQTT_CLIENTS_PWD).build();
    }
}
