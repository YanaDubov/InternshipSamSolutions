package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.Image;
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
    public class ImageRepositoryTest {
        @Autowired
        private ImageRepository imageRepository;

        @Test
        public void createAndCheckIfExist() {
            Image createdImage = imageRepository.save(createTestEntity(1));
            assertTrue(imageRepository.existsById(createdImage.getId()));
        }
        @Test
        public void createAndGetByIdEntity() {
            Image createdImage = imageRepository.save(createTestEntity(1));
            Image actualImage = imageRepository.findById(createdImage.getId()).get();
            assertEquals(createdImage, actualImage);
        }

        @Test
        public void deleteByIdNotExist() {
            Image createdImage = imageRepository.save(createTestEntity(1));
            imageRepository.deleteById(createdImage.getId());
            assertFalse(imageRepository.existsById(createdImage.getId()));
        }

        @Test
        public void createEntitiesAndGetAll() {
            Image createdImage1 = imageRepository.save(createTestEntity(1));
            Image createdImage2 = imageRepository.save(createTestEntity(2));
            List<Image> list = new ArrayList<>();
            imageRepository.findAll().forEach(list::add);
            assertEquals(list.get(0), createdImage1);
            assertEquals(list.get(1), createdImage2);

        }

        private Image createTestEntity(Integer id) {
            Image image = new Image();
            image.setId(id);
            image.setUrl("Test");
            return image;
        }
}