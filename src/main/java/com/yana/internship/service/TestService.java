package com.yana.internship.service;

import com.yana.internship.bean.TestBean;
import com.yana.internship.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestService {
    TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestBean> getAll(){
        List<TestBean> all = testRepository.getAll();
        return all;
    }

    public TestBean getById(int id){
        return testRepository.getById(id);
    }

    public TestBean deleteById(int id)
    {
        return testRepository.deleteById(id);
    }

    public TestBean create(TestBean bean){
        return testRepository.create(bean);
    }

    public TestBean update(TestBean bean){
         return testRepository.update(bean);
    }
}
