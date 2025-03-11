package com.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.enums.OrderStatus;
import com.ecommerce.repo.IOrdersRepository;

import jakarta.persistence.criteria.Order;


@Service("OrderService")
public class OrderServiceImpl implements IOrderService{
	
	
	@Autowired
	private IOrdersRepository repository;

	@Override
	public Orders createOrder(Orders order) {
		return repository.save(order);
	}

	@Override
	public Orders getOrderById(Integer orderId) {
		return repository.findById(orderId).get();
	}

	@Override
	public String updateOrderStatus(Integer orderId, OrderStatus status) {
		Optional<Orders> opt=repository.findById(orderId);
		if(opt.isPresent()) {
			Orders orders=opt.get();
			orders.setStatus(status);
			if(status.equals("DELIVERED")) {
				orders.setDeliveryDate(new Date());
			}
			return "Status is updated";
			
		}
		else {
			return orderId+"doctor not fount";
		}
		
	}

	@Override
	public List<Orders> getAllOrders() {
		return repository.findAll();
	}

}
