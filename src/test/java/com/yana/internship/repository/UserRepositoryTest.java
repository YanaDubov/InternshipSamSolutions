package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

    @WebAppConfiguration
    @ContextConfiguration(classes = {WebConfig.class, JpaConfig.class})
    @RunWith(SpringJUnit4ClassRunner.class)
    @Transactional
    public class UserRepositoryTest {
        @Autowired
        private UserRepository userRepository;

        @Test
        public void createAndCheckIfExist() {
            User createdUser = userRepository.save(createTestEntity(1L));
            assertTrue(userRepository.existsById(createdUser.getId()));
        }
        @Test
        public void createAndGetByIdEntity() {
            User createdUser = userRepository.save(createTestEntity(1L));
            User actualUser = userRepository.findById(createdUser.getId()).get();
            assertEquals(createdUser, actualUser);
        }

        @Test
        public void deleteByIdNotExist() {
            User createdUser = userRepository.save(createTestEntity(1L));
            userRepository.deleteById(createdUser.getId());
            assertFalse(userRepository.existsById(createdUser.getId()));
        }

        @Test
        public void createEntitiesAndGetAll() {
            User createdUser1 = userRepository.save(createTestEntity(1L));
            User createdUser2 = userRepository.save(createTestEntity(2L));
            List<User> list = new ArrayList<>();
            userRepository.findAll().forEach(list::add);
            assertEquals(list.get(0), createdUser1);
            assertEquals(list.get(1), createdUser2);

        }

        private User createTestEntity(Long id) {
            User user = new User();
            user.setId(id);
            user.setName("Test");
            user.setSurname("Test");
            user.setEmail("Test");
            user.setSalt("Test");
            user.setPassword("Test");
            return user;
        }
}