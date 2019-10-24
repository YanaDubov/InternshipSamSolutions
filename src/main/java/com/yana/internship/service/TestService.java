package com.yana.internship.service;

import com.yana.internship.bean.TestBean;
import com.yana.internship.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestService {
    private static final Logger logger
            = LoggerFactory.getLogger(TestService.class);
    TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestBean> getAll(){
        logger.info("Start loading all beans");
        List<TestBean> all = testRepository.getAll();
        logger.info("{} number of beans has been loaded",all.size());
        return all;
    }

    public TestBean getById(int id){
        TestBean bean = testRepository.getById(id);
        logger.info("Got bean by id {}", bean.getId());
        return bean;
    }

    public TestBean deleteById(int id)
    {
        logger.info("Delete bean by id {}", id);
        return testRepository.deleteById(id);
    }

    public TestBean create(TestBean bean){

        TestBean testBean = testRepository.create(bean);
        logger.info("Bean with id {} has been created", testBean.getId());
        return testBean;
    }

    public TestBean update(TestBean bean){

        TestBean testBean = testRepository.update(bean);
        logger.info("Bean with id {} has been updated", testBean.getId());
        return testBean;
    }
}
