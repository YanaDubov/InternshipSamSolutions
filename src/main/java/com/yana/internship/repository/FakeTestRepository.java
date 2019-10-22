package com.yana.internship.repository;

import com.yana.internship.bean.TestBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class FakeTestRepository implements TestRepository {

    Map<Integer, TestBean> testBeanMap = new HashMap<>();

    @Override
    public boolean update(TestBean bean) {
        if (testBeanMap.containsKey(bean.getId())){
            testBeanMap.put(bean.getId(),bean);
            return true;
        }
        else return false;
    }

    @Override
    public void put(TestBean bean) {
        testBeanMap.put(bean.getId(),bean);
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
    public void deleteById(int id) {
        testBeanMap.remove(id);
    }

}
