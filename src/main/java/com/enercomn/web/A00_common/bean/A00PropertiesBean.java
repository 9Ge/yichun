package com.enercomn.web.A00_common.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class A00PropertiesBean {

    @Value("${spring.profiles.active}")
    private String env;
}
