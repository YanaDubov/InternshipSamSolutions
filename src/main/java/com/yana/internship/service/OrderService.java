package com.yana.internship.service;

import com.yana.internship.dto.OrderDTO;
import com.yana.internship.entity.Apartment;
import com.yana.internship.entity.Order;
import com.yana.internship.entity.User;
import com.yana.internship.repository.ApartmentRepository;
import com.yana.internship.repository.OrderRepository;
import com.yana.internship.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ApartmentRepository apartmentRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public Order create(OrderDTO orderDTO) {
        logger.info("Create order");
        Apartment apartment = apartmentRepository.findById(orderDTO.getApartmentId()).get();
        Order order = new Order();
        order.setApartment(apartment);
        order.setCreationDate(orderDTO.getCreationDate());
        order.setCheckInDate(orderDTO.getCheckInDate());
        order.setCheckOutDate(orderDTO.getCheckOutDate());
        User user = userRepository.findById(orderDTO.getUser()).get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
        logger.info("Delete order with id {}", id);
    }
}
