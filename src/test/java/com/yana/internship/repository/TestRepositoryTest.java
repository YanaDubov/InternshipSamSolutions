package com.yana.internship.repository;

import com.yana.internship.config.HibernateConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestRepositoryTest {

    @Autowired
    private TestRepository hibernateTestRepository;

    @Test
    public void createAndGetByIdEntity() {
        TestEntity createdTestEntity = hibernateTestRepository.create(createTestEntity("Nana"));
        TestEntity actualEntity = hibernateTestRepository.getById(createdTestEntity.getId());
        assertEquals(createdTestEntity, actualEntity);
    }

    @Test
    public void createEntityAndUpdateNameField() {
        TestEntity createdTestEntity = hibernateTestRepository.create(createTestEntity("Nana"));
        createdTestEntity.setName("Ann");
        TestEntity actualEntity = hibernateTestRepository.update(createdTestEntity);
        assertEquals(createdTestEntity, actualEntity);
    }

    @Test
    public void deleteByIdShouldReturnCorrectResult() {
        TestEntity createdTestEntity = hibernateTestRepository.create(createTestEntity("Nana"));
        int id = hibernateTestRepository.deleteById(createdTestEntity.getId());
        assertEquals(createdTestEntity.getId(), id);
    }
    @Test
    public void getByIdShouldReturnNullAfterDelete() {
        TestEntity createdTestEntity = hibernateTestRepository.create(createTestEntity("Nana"));
        TestEntity actualEntity = hibernateTestRepository.getById(hibernateTestRepository.deleteById(createdTestEntity.getId()));
        assertNull(actualEntity);
    }

    @Test
    public void createEntitiesAndGetAll() {
        TestEntity actualEntity1 = hibernateTestRepository.create(createTestEntity("Nana"));
        TestEntity actualEntity2 = hibernateTestRepository.create(createTestEntity("Ann"));
        List<TestEntity> list = hibernateTestRepository.getAll();
        assertEquals(list.get(0), actualEntity1);
        assertEquals(list.get(1), actualEntity2);

    }

    private TestEntity createTestEntity(String nana) {
        TestEntity testEntity = new TestEntity();
        testEntity.setName(nana);
        return testEntity;
    }
}