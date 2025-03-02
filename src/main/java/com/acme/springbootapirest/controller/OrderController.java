package com.acme.springbootapirest.controller;

import com.acme.springbootapirest.dto.OrderRequestDto;
import com.acme.springbootapirest.dto.OrderResponseDto;
import com.acme.springbootapirest.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/send")
    public OrderResponseDto sendOrder(@Valid @RequestBody OrderRequestDto order) throws Exception {
        return orderService.sendOrder(order);
    }
}