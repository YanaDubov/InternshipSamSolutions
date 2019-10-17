package com.yana.internship;

import org.springframework.beans.factory.annotation.Autowired;

public class TestService {
    TestRepositoryImpl testRepository;
    @Autowired
    public TestService(TestRepositoryImpl testRepository) {
        this.testRepository = testRepository;
    }
}
