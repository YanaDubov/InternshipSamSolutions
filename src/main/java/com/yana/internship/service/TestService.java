package com.yana.internship.service;

import com.yana.internship.entity.TestEntity;
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
        List<TestEntity> all = (List<TestEntity>) testRepository.findAll();
        logger.info("{} number of beans has been loaded", all.size());
        return all;
    }

    @Transactional
    public TestEntity getById(Long id) {
        TestEntity bean = testRepository.findById(id).get();
        logger.info("Got entity by id {}", bean.getId());
        return bean;
    }

    @Transactional
    public void deleteById(Long id) {
        logger.info("Delete entity by id {}", id);
        testRepository.deleteById(id);
    }

    @Transactional
    public TestEntity create(TestEntity bean) {
        TestEntity testEntity = testRepository.save(bean);
        logger.info("Bean with id {} has been created", testEntity.getId());
        return testEntity;
    }

    @Transactional
    public TestEntity update(TestEntity bean) {
        TestEntity testEntity = testRepository.save(bean);
        logger.info("Bean with id {} has been updated", testEntity.getId());
        return testEntity;
    }
}
