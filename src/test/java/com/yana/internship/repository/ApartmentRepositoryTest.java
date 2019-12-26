package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.Address;
import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Country;
import com.yana.internship.entity.Tariff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, JpaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ApartmentRepositoryTest {
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createAndGetById() {
        Apartment createdApartment = apartmentRepository.save(createApartment(1L));
        Apartment actualApartment = apartmentRepository.findById(createdApartment.getId()).get();
        assertEquals(actualApartment, createdApartment);
    }

    @Test
    public void createAndCheckIfExist() {
        Apartment createdApartment = apartmentRepository.save(createApartment(1L));
        assertTrue(apartmentRepository.existsById(createdApartment.getId()));
    }

    @Test
    public void deleteByIdEntityNotExist() {
        Apartment createdApartment = apartmentRepository.save(createApartment(1L));
        apartmentRepository.deleteById(createdApartment.getId());
        assertFalse(apartmentRepository.existsById(createdApartment.getId()));
    }

    @Test
    public void createApartmentsAndGetAll() throws ParseException {
        Apartment createdApartment1 = apartmentRepository.save(createApartment(1L));
        Apartment createdApartment2 = apartmentRepository.save(createApartment(2L));
        List<Apartment> list = new ArrayList<>();
        apartmentRepository.findAll().forEach(list::add);
        assertEquals(list.get(0), createdApartment1);
        assertEquals(list.get(1), createdApartment2);
    }

    private Apartment createApartment(Long id) {
        Apartment apartment = new Apartment();
        apartment.setId(id);
        apartment.setName("Test");
        apartment.setDescription("Test");

        Address address = new Address();
        address.setId(id);
        address.setCountry(Country.BY);
        address.setCity("Test");
        address.setAddress("Test");
        apartment.setAddress(address);
        addressRepository.save(address);

        Tariff tariff = new Tariff();
        tariff.setId(id);
        tariff.setCostPerNight(BigDecimal.valueOf(1));
        apartment.setTariff(tariff);
        tariffRepository.save(tariff);
        return apartment;
    }
}