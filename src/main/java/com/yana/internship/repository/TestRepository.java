package com.yana.internship.repository;

import com.yana.internship.bean.TestBean;

import java.util.List;

public interface TestRepository {
    void put(TestBean bean);

    public TestBean getById(int id);

    public List<TestBean> getAll();

    public void deleteById(int id);

}
