package com.yana.internship.service;

import com.yana.internship.exception.BusinessLogicException;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ApartmentRepository apartmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final PriceCalculatorService priceCalculatorService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ApartmentRepository apartmentRepository, PriceCalculatorService priceCalculatorService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.apartmentRepository = apartmentRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    public Order create(OrderDTO orderDTO, String email) {
        logger.info("Create order");
        Apartment apartment = apartmentRepository.findById(orderDTO.getApartmentId()).orElseThrow(()->new BusinessLogicException("No apartment with id " + orderDTO.getApartmentId()));
        Order order = new Order();
        order.setApartment(apartment);
        order.setPrice(orderDTO.getPrice());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setCheckInDate(orderDTO.getCheckInDate());
        order.setCheckOutDate(orderDTO.getCheckOutDate());
        User user = userRepository.findByEmail(email);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
        logger.info("Delete order with id {}", id);
    }

    public BigDecimal countPrice(LocalDate checkIn, LocalDate checkOut, Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(()->new BusinessLogicException("No apartment with id " + id));
        return priceCalculatorService.countTotalPrice(checkIn,checkOut,apartment.getTariff());
    }
}
