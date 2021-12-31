package com.example.webX.backend.TechnicalAssignment.common.config;


import com.example.webX.backend.TechnicalAssignment.common.Utils.AuthLoggedUser;
import com.example.webX.backend.TechnicalAssignment.common.Utils.DataBindConverter;
import com.example.webX.backend.TechnicalAssignment.common.Utils.RandomIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RandomIdUtil uuid() {
        return new RandomIdUtil();
    }

    @Bean
    public AuthLoggedUser getLoggingUser(){
        return new AuthLoggedUser();
    }

    @Bean
    public DataBindConverter fetchDataBind(){
        return new DataBindConverter();
    }

}
