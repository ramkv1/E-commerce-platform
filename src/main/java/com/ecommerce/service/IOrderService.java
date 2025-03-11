package com.ecommerce.service;

import java.util.List;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.enums.OrderStatus;

public interface IOrderService {

	public Orders createOrder(Orders order);
	public Orders getOrderById(Integer orderId);
	public String updateOrderStatus(Integer orderId,OrderStatus status);
	public List<Orders> getAllOrders();
}
