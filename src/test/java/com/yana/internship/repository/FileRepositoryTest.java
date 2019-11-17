package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.File;
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
    public class FileRepositoryTest {
        @Autowired
        private FileRepository fileRepository;

        @Test
        public void createAndCheckIfExist() {
            File createdFile = fileRepository.save(createTestEntity(1));
            assertTrue(fileRepository.existsById(createdFile.getId()));
        }
        @Test
        public void createAndGetByIdEntity() {
            File createdFile = fileRepository.save(createTestEntity(1));
            File actualFile = fileRepository.findById(createdFile.getId()).get();
            assertEquals(createdFile, actualFile);
        }

        @Test
        public void deleteByIdNotExist() {
            File createdFile = fileRepository.save(createTestEntity(1));
            fileRepository.deleteById(createdFile.getId());
            assertFalse(fileRepository.existsById(createdFile.getId()));
        }

        @Test
        public void createEntitiesAndGetAll() {
            File createdFile1 = fileRepository.save(createTestEntity(1));
            File createdFile2 = fileRepository.save(createTestEntity(2));
            List<File> list = new ArrayList<>();
            fileRepository.findAll().forEach(list::add);
            assertEquals(list.get(0), createdFile1);
            assertEquals(list.get(1), createdFile2);

        }

        private File createTestEntity(Integer id) {
            File file = new File();
            file.setId(id);
            file.setUrl("Test");
            file.setFile(new byte[0]);
            return file;
        }
}