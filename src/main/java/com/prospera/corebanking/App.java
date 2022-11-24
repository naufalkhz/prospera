package com.prospera.corebanking;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

    //demo at minute 47.01
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
