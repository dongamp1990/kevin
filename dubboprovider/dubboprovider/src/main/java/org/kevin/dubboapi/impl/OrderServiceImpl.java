package org.kevin.dubboapi.impl;

import org.kevin.dubboapi.domain.Order;
import org.kevin.dubboapi2.OrderService;
import org.springframework.stereotype.Component;

public class OrderServiceImpl implements OrderService {

	public Order getOrder() {
		Order order = new Order();
		order.setAmount(100D);
		order.setOrderId(1L);
		order.setProductName("Netty实战");
		return order;
	}

}
