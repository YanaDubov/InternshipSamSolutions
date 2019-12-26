package com.yana.internship.service;

import com.yana.internship.dto.OrderDTO;
import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Order;
import com.yana.internship.entity.User;
import com.yana.internship.repository.ApartmentRepository;
import com.yana.internship.repository.OrderRepository;
import com.yana.internship.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Spy
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ApartmentRepository apartmentRepository;
    @Mock
    private User user;
    @Mock
    private Apartment apartment;
    @Mock
    private Order order;

    @Test
    public void createShouldReturnCorrectResult() {
        Long id = 1L;
        OrderDTO orderDTO = createOrderDTO(id);
        String email = "test";
        when(apartmentRepository.findById(id)).thenReturn(Optional.ofNullable(apartment));
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        assertEquals(order, orderService.create(orderDTO, email));
    }

    @Test
    public void createShouldCallRepository() {
        Long id = 1L;
        OrderDTO orderDTO = createOrderDTO(id);
        String email = "test";
        when(apartmentRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(apartment));
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        orderService.create(orderDTO, email);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    public void deleteShouldCallRepository() {
        orderService.delete(1L);
        verify(orderRepository).deleteById(1L);
    }

    private OrderDTO createOrderDTO(Long id) {
        LocalDate date = mock(LocalDate.class);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(id);
        orderDTO.setApartmentId(id);
        orderDTO.setCheckInDate(date);
        orderDTO.setCheckOutDate(date);
        orderDTO.setCreationDate(date);
        orderDTO.setUserEmail("Test");
        return orderDTO;
    }
}