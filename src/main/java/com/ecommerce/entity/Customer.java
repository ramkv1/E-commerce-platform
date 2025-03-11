package com.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@NamedStoredProcedureQuery(name = "Customer.getCustomerByCustomerName",
procedureName = "Get_Customer_By_Customer_Name",parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN,type = String.class,name = "customer_Name"),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name="CustomerId", type=Integer.class)
})
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	@NonNull
	private String firstName;
	@NonNull
	private Date createdAt = new Date(); 
	@NonNull
	private String lastName;
	@NonNull
	private Integer customerAge;
	@NonNull
	private String customerEmailId;
	@JsonManagedReference("customer-address")
	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Address> addresses;
	@JsonManagedReference("customer-order")
	@OneToMany(targetEntity = Orders.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "customer")
	private List<Orders> orders;
}
