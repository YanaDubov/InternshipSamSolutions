package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.Role;
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
    public class RoleRepositoryTest {
        @Autowired
        private RoleRepository roleRepository;

        @Test
        public void createAndCheckIfExist() {
            Role createdRole = roleRepository.save(createTestEntity(1));
            assertTrue(roleRepository.existsById(createdRole.getId()));
        }

        @Test
        public void createAndGetByIdEntity() {
            Role createdRole = roleRepository.save(createTestEntity(1));
            Role actualRole = roleRepository.findById(createdRole.getId()).get();
            assertEquals(createdRole, actualRole);
        }

        @Test
        public void deleteByIdNotExist() {
            Role createdRole = roleRepository.save(createTestEntity(1));
            roleRepository.deleteById(createdRole.getId());
            assertFalse(roleRepository.existsById(createdRole.getId()));
        }

        @Test
        public void createEntitiesAndGetAll() {
            Role createdRole1 = roleRepository.save(createTestEntity(1));
            Role createdRole2 = roleRepository.save(createTestEntity(2));
            List<Role> list = new ArrayList<>();
            roleRepository.findAll().forEach(list::add);
            assertEquals(list.get(0), createdRole1);
            assertEquals(list.get(1), createdRole2);

        }

        private Role createTestEntity(Integer id) {
            Role role = new Role();
            role.setId(id);
            role.setName("Test");
            return role;
        }

}