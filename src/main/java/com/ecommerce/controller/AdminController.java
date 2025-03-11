package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.enums.OrderStatus;
import com.ecommerce.service.IOrderService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IOrderService service;
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		return new ResponseEntity<String>("  "+service.getAllOrders(),HttpStatus.OK);
	}
	
	@PutMapping("orders/{id}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable Integer id,@PathVariable OrderStatus staus){
		return new ResponseEntity<String>("  "+service.updateOrderStatus(id,staus),HttpStatus.OK);
	}
}
