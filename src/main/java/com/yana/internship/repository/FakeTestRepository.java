package com.yana.internship.repository;

import com.yana.internship.bean.TestBean;
import com.yana.internship.config.BusinessLogicException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FakeTestRepository implements TestRepository {

    Map<Integer, TestBean> testBeanMap = new HashMap<>();

    @Override
    public TestBean update(TestBean bean) {
        if (testBeanMap.containsKey(bean.getId())){
            testBeanMap.put(bean.getId(),bean);
            return testBeanMap.get(bean.getId());
        }
        else throw new BusinessLogicException("");
    }

    @Override
    public TestBean create(TestBean bean) {
        testBeanMap.put(bean.getId(),bean);
        return testBeanMap.get(bean.getId());
    }

    @Override
    public TestBean getById(int id) {
        return testBeanMap.get(id);
    }

    @Override
    public List<TestBean> getAll() {
        return new ArrayList<>(testBeanMap.values());
    }

    @Override
    public TestBean deleteById(int id)
    {
        TestBean bean = testBeanMap.get(id);
        testBeanMap.remove(id);
        return bean;
    }

}
