package com.yana.internship.repository;

import com.yana.internship.entity.Lang;
import com.yana.internship.entity.TestEntity;

import java.util.List;

public interface TestRepository {
    TestEntity update(TestEntity bean);

    TestEntity create(TestEntity bean);

    TestEntity getById(int id, Lang lang);

    List<TestEntity> getAll(Lang lang);

    int deleteById(int id);

}
