package com.yana.internship;

import java.util.List;

public interface TestRepositoryImpl {
    public void put(TestBean bean);

    public TestBean getById(int id);

    public List<TestBean> getAll();

    public void deleteById(int id);

}
