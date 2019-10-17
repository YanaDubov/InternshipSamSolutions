package com.yana.internship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeTestRepoitory implements TestRepositoryImpl {

    Map<Integer,TestBean> testBeanMap = new HashMap<>();

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
        return (List<TestBean>) testBeanMap.values();
    }

    @Override
    public void deleteById(int id) {
        testBeanMap.remove(id);
    }

}
