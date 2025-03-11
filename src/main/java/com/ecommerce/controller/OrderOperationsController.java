package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.enums.OrderStatus;
import com.ecommerce.service.IOrderService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/orders")
public class OrderOperationsController {
	
	@Autowired
	private IOrderService iOrderService;
	
	
	@PostMapping(value ="/order",consumes ="application/json", produces = "application/json")
	public ResponseEntity<Orders> createAnOrder(@RequestBody Orders orders){
		return new ResponseEntity<Orders>(iOrderService.createOrder(orders),HttpStatus.CREATED);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Orders> findTheOrderById(@PathVariable("id") Integer oId){
		return new ResponseEntity<Orders>(iOrderService.getOrderById(oId),HttpStatus.OK);
	}
//	@PutMapping("path/{id}")
//	public ResponseEntity<?> putMethodName(@PathVariable Integer id, OrderStatus status) {
//		return new ResponseEntity<String>(iOrderService.updateOrderStatus(id, status),HttpStatus.ACCEPTED);
//	}

}
