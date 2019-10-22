package com.yana.internship.repository;

import com.yana.internship.bean.TestBean;

import java.util.List;

public interface TestRepository {
    TestBean update(TestBean bean);

    TestBean create(TestBean bean);

    public TestBean getById(int id);

    public List<TestBean> getAll();

    public TestBean deleteById(int id);

}
