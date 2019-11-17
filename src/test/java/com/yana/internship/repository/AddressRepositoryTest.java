package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.Address;
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
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void createAndCheckIfExist() {
        Address createdAddress = addressRepository.save(createTestEntity(1));
        assertTrue(addressRepository.existsById(createdAddress.getId()));
    }
    @Test
    public void createAndGetByIdEntity() {
        Address createdAddress = addressRepository.save(createTestEntity(1));
        Address actualAddress = addressRepository.findById(createdAddress.getId()).get();
        assertEquals(createdAddress, actualAddress);
    }

    @Test
    public void deleteByIdNotExist() {
        Address createdAddress = addressRepository.save(createTestEntity(1));
        addressRepository.deleteById(createdAddress.getId());
        assertFalse(addressRepository.existsById(createdAddress.getId()));
    }

    @Test
    public void createEntitiesAndGetAll() {
        Address createdAddress1 = addressRepository.save(createTestEntity(1));
        Address createdAddress2 = addressRepository.save(createTestEntity(2));
        List<Address> list = new ArrayList<>();
        addressRepository.findAll().forEach(list::add);
        assertEquals(list.get(0), createdAddress1);
        assertEquals(list.get(1), createdAddress2);

    }

    private Address createTestEntity(Integer id) {
        Address address = new Address();
        address.setId(id);
        address.setCountry("Test");
        address.setCity("Test");
        address.setAddress("Test");
        return address;
    }
}