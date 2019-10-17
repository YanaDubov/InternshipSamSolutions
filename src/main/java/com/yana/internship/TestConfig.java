package com.yana.internship;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    TestRepositoryImpl testRepository(){
        return new FakeTestRepoitory();

    }
}
