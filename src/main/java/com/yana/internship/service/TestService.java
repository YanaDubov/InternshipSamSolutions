package com.yana.internship.service;

import com.yana.internship.bean.TestEntity;
import com.yana.internship.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestService.class);
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Transactional
    public List<TestEntity> getAll() {
        logger.info("Start loading all beans");
        List<TestEntity> all = testRepository.getAll();
        logger.info("{} number of beans has been loaded", all.size());

        //logic to test
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Yana");
        all.add(testEntity);

        return all;
    }

    @Transactional
    public TestEntity getById(int id) {
        TestEntity bean = testRepository.getById(id);
        logger.info("Got bean by id {}", bean.getId());
        return bean;
    }

    @Transactional
    public int deleteById(int id) {
        logger.info("Delete bean by id {}", id);
        return testRepository.deleteById(id);
    }

    @Transactional
    public TestEntity create(TestEntity bean) {

        TestEntity testEntity = testRepository.create(bean);
        logger.info("Bean with id {} has been created", testEntity.getId());
        return testEntity;
    }

    @Transactional
    public TestEntity update(TestEntity bean) {

        TestEntity testEntity = testRepository.update(bean);
        logger.info("Bean with id {} has been updated", testEntity.getId());
        return testEntity;
    }
}
