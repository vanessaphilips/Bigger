package com.example.project_bigbangk;

import com.example.project_bigbangk.model.BigBangkApplicatie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class ProjectBigBangKApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjectBigBangKApplication.class, args);
    }

}


