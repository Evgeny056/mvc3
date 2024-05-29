package com.mvc3.service;

import com.mvc3.model.entity.Order;

public interface OrderService {

    Order createOrder(Order order);

    Order findOrderById(Long id);
}
