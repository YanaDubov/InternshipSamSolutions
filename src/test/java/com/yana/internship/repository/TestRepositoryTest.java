package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
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

import static org.junit.Assert.*;

    @WebAppConfiguration
    @ContextConfiguration(classes = {WebConfig.class, JpaConfig.class})
    @RunWith(SpringJUnit4ClassRunner.class)
    @Transactional
    public class TestRepositoryTest {
        @Autowired
        private TestRepository testRepository;

        @Test
        public void createAndCheckIfExist() {
            TestEntity createdEntity = testRepository.save(createTestEntity(1L));
            assertTrue(testRepository.existsById(createdEntity.getId()));
        }
        @Test
        public void createAndGetByIdEntity() {
            TestEntity createdEntity = testRepository.save(createTestEntity(1L));
            TestEntity actualEntity = testRepository.findById(createdEntity.getId()).get();
            assertEquals(createdEntity, actualEntity);
        }

        @Test
        public void deleteByIdNotExist() {
            TestEntity createdEntity = testRepository.save(createTestEntity(1L));
            testRepository.deleteById(createdEntity.getId());
            assertFalse(testRepository.existsById(createdEntity.getId()));
        }

        @Test
        public void createEntitiesAndGetAll() {
            TestEntity createdEntity1 = testRepository.save(createTestEntity(1L));
            TestEntity createdEntity2 = testRepository.save(createTestEntity(2L));
            List<TestEntity> list = (List<TestEntity>) testRepository.findAll();
            assertEquals(list.get(0), createdEntity1);
            assertEquals(list.get(1), createdEntity2);

        }

        private TestEntity createTestEntity(Long id) {
            TestEntity tariff = new TestEntity();
            tariff.setId(id);
            tariff.setName("Test");
            return tariff;
        }
}