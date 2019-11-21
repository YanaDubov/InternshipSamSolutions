package com.yana.internship.repository;

import com.yana.internship.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestEntity,Long> {
}
