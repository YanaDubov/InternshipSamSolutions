package com.yana.internship.repository;

import com.yana.internship.bean.TestEntity;
import com.yana.internship.config.BusinessLogicException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: 2019-10-29 delete me
public class FakeTestRepository implements TestRepository {

    Map<Integer, TestEntity> testBeanMap = new HashMap<>();

    @Override
    public TestEntity update(TestEntity bean) {
        if (testBeanMap.containsKey(bean.getId())){
            testBeanMap.put(bean.getId(),bean);
            return testBeanMap.get(bean.getId());
        }
        else throw new BusinessLogicException("");
    }

    @Override
    public TestEntity create(TestEntity bean) {
        testBeanMap.put(bean.getId(),bean);
        return testBeanMap.get(bean.getId());
    }

    @Override
    public TestEntity getById(int id) {
        return testBeanMap.get(id);
    }

    @Override
    public List<TestEntity> getAll() {
        return new ArrayList<>(testBeanMap.values());
    }

    @Override
    public TestEntity deleteById(int id)
    {
        TestEntity bean = testBeanMap.get(id);
        testBeanMap.remove(id);
        return bean;
    }

}
