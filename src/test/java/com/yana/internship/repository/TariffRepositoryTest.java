package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.Tariff;
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
public class TariffRepositoryTest {
    @Autowired
    private TariffRepository tariffRepository;

    @Test
    public void createAndCheckIfExist() {
        Tariff createdTariff = tariffRepository.save(createTestEntity(1));
        assertTrue(tariffRepository.existsById(createdTariff.getId()));
    }
    @Test
    public void createAndGetByIdEntity() {
        Tariff createdTariff = tariffRepository.save(createTestEntity(1));
        Tariff actualTariff = tariffRepository.findById(createdTariff.getId()).get();
        assertEquals(createdTariff, actualTariff);
    }

    @Test
    public void deleteByIdNotExist() {
        Tariff createdTariff = tariffRepository.save(createTestEntity(1));
        tariffRepository.deleteById(createdTariff.getId());
        assertFalse(tariffRepository.existsById(createdTariff.getId()));
    }

    @Test
    public void createEntitiesAndGetAll() {
        Tariff createdTariff1 = tariffRepository.save(createTestEntity(1));
        Tariff createdTariff2 = tariffRepository.save(createTestEntity(2));
        List<Tariff> list = new ArrayList<>();
        tariffRepository.findAll().forEach(list::add);
        assertEquals(list.get(0), createdTariff1);
        assertEquals(list.get(1), createdTariff2);

    }

    private Tariff createTestEntity(Integer id) {
        Tariff tariff = new Tariff();
        tariff.setId(id);
        tariff.setCostPerNight(1);
        return tariff;
    }
}