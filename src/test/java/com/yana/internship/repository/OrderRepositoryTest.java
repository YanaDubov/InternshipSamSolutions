package com.yana.internship.repository;

import com.yana.internship.config.JpaConfig;
import com.yana.internship.config.WebConfig;
import com.yana.internship.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, JpaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TariffRepository tariffRepository;
    @Autowired
    AddressRepository addressRepository;

    @Test
    public void createAndGetByIdEntity() {
        Order createdOrder = orderRepository.save(createTestEntity(1));
        Order actualOrder = orderRepository.findById(createdOrder.getId()).get();
        assertEquals(createdOrder, actualOrder);
    }

    @Test
    public void createAndCheckIfExist() {
        Order createdOrder = orderRepository.save(createTestEntity(1));
        assertTrue(orderRepository.existsById(createdOrder.getId()));
    }

    @Test
    public void deleteByIdNotExist() {
        Order createdOrder = orderRepository.save(createTestEntity(1));
        orderRepository.deleteById(createdOrder.getId());
        assertFalse(orderRepository.existsById(createdOrder.getId()));
    }

    @Test
    public void createEntitiesAndGetAll() {
        Order createdOrder1 = orderRepository.save(createTestEntity(1));
        Order createdOrder2 = orderRepository.save(createTestEntity(2));
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().forEach(list::add);
        assertEquals(list.get(0), createdOrder1);
        assertEquals(list.get(1), createdOrder2);

    }

    private Order createTestEntity(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setCreationDate(new Date());
        order.setCheckInDate(new Date());
        order.setCheckOutDate(new Date());

        Apartment apartment = new Apartment();
        apartment.setId(id);
        apartment.setName("Test");
        apartment.setDescription("Test");

        Address address =  new Address();
        address.setId(id);
        address.setCountry("Test");
        address.setCity("Test");
        address.setAddress("Test");
        apartment.setAddress(address);
        addressRepository.save(address);

        Tariff tariff = new Tariff();
        tariff.setId(id);
        tariff.setCostPerNight(1);
        apartment.setTariff(tariff);
        order.setApartment(apartment);
        tariffRepository.save(tariff);

        User user = new User();
        user.setId(id);
        user.setName("Test");
        user.setEmail("Test");
        user.setPassword("test");
        user.setSalt("salt");
        user.setSurname("test");
        order.setUser(user);
        userRepository.save(user);

        apartmentRepository.save(apartment);

        return order;
    }
}