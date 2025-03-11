package com.ecommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.ecommerce.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Orders implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	@NonNull
    private Date orderDateTime=new Date();
	@Nonnull
	private Double total_Amount;
	@Nonnull
	private OrderStatus status;
	@JsonManagedReference("Order-OrderItem")
	@OneToMany(targetEntity = OrderItem.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "allorders")
	private List<OrderItem> orderItems;
	@JsonBackReference("customer-order")
	@ManyToOne(targetEntity = Customer.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "cid",referencedColumnName = "customerId")
	private Customer customer;
	//Delivery Date
	private Date DeliveryDate=null;
	
	
	
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDateTime=" + orderDateTime + ", total_Amount=" + total_Amount
				+ ", status=" + status + "]";
	}
	
	

}
