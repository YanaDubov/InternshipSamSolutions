package com.yana.internship.config;

import com.yana.internship.repository.FakeTestRepository;
import com.yana.internship.repository.TestRepository;
import com.yana.internship.service.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.yana.internship")
public class WebConfig {

}
