package com.ecommerce.entity;

import java.io.Serializable;
import java.util.List;

import com.ecommerce.entity.enums.Cities;
import com.ecommerce.entity.enums.Country;
import com.ecommerce.entity.enums.IndianState;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String street;
	private Cities city;
	private IndianState state;
	private Integer postalCode;
	private Country country;
    @JsonBackReference("customer-address") // Marks this as the back part of the reference
	@ManyToOne(targetEntity = Customer.class,cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(name = "cid",referencedColumnName = "customerId")
    private Customer customer; // No need for Cascade here since it's managed by Customer
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + "]";
	}
    
    

}
