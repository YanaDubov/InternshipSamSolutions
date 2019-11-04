package com.yana.internship.repository;

import com.yana.internship.config.HibernateConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.TestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateTestRepositoryTest {

    @Autowired
    private TestRepository hibernateTestRepository;

    @Test
    @Transactional()
    @Rollback()
    public void createTest() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Nana");
        TestEntity createdTestEntity = hibernateTestRepository.create(testEntity);
        final int testId = createdTestEntity.getId();
        TestEntity test = hibernateTestRepository.getById(testId);
        Assert.assertEquals(testEntity.getName(), test.getName());
    }

    @Test
    @Transactional()
    @Rollback()
    public void updateTest() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Nana");
        TestEntity createdTestEntity = hibernateTestRepository.create(testEntity);
        final int testId = createdTestEntity.getId();
        TestEntity test = hibernateTestRepository.getById(testId);
        Assert.assertEquals(testEntity.getName(), test.getName());

        testEntity.setName("Ann");
        hibernateTestRepository.update(testEntity);
        TestEntity test2 = hibernateTestRepository.getById(testId);
        Assert.assertEquals(testEntity.getName(), test2.getName());
    }

    @Test
    @Transactional()
    @Rollback()
    public void deleteByIdTest() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Nana");
        TestEntity createdTestEntity = hibernateTestRepository.create(testEntity);
        final int testId = createdTestEntity.getId();
        TestEntity test = hibernateTestRepository.getById(testId);
        Assert.assertEquals(testEntity.getName(), test.getName());
        int id = hibernateTestRepository.deleteById(testId);
        Assert.assertEquals(id, testId);
    }

    @Test
    @Transactional()
    @Rollback()
    public void getAllTest() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("Nana");
        TestEntity testEntity2 = new TestEntity();
        testEntity2.setName("Ann");
        hibernateTestRepository.create(testEntity);
        hibernateTestRepository.create(testEntity2);
        List<TestEntity> list = hibernateTestRepository.getAll();
        list.forEach(e -> System.out.println(e.getId() + " " + e.getName()));
        Assert.assertEquals(list.get(0).getName(), testEntity.getName());
        Assert.assertEquals(list.get(1).getName(), testEntity2.getName());

    }

    @Test
    @Transactional()
    @Rollback()
    public void getNotExistEntity() {
        TestEntity testEntity = hibernateTestRepository.getById(45);
        Assert.assertNull(testEntity);
    }
}