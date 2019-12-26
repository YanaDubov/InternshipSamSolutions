package com.yana.internship.controller;

import com.yana.internship.dto.OrderDTO;
import com.yana.internship.entity.Order;
import com.yana.internship.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order create(@RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        return orderService.create(orderDTO, request.getUserPrincipal().getName());
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }

    @GetMapping("/price")
    public @ResponseBody
    BigDecimal countTotalPrice(@RequestParam Long id,
                               @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate checkInDate,
                               @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate checkOutDate) {
        return orderService.countPrice(checkInDate, checkOutDate, id);
    }
}
