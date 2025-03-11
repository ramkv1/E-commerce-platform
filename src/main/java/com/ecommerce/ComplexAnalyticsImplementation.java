package com.ecommerce;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.enums.Categories;
import com.ecommerce.repo.IOrdersRepository;

public class ComplexAnalyticsImplementation {
	public static void main(String[] args) {
		
	}
	
	//Most Profitable Customers
	public List<CustomerRevenue> getTopCustomers() {
	    List<Object[]> results = IOrdersRepository.findCustomerRevenue();
	    return results.stream().map(obj -> new CustomerRevenue(
	        (String) obj[0], (Double) obj[1], (Double) obj[2], (Double) obj[1] - (Double) obj[2])
	    ).collect(Collectors.toList());
	}
	
	
	//Monthly Retention Rate
	public double calculateRetentionRate() {
	    List<Long> previousMonthCustomers = IOrdersRepository.getCustomersByMonth(LocalDate.now().minusMonths(1));
	    List<Long> currentMonthCustomers = IOrdersRepository.getCustomersByMonth(LocalDate.now());

	    long returningCustomers = previousMonthCustomers.stream()
	            .filter(currentMonthCustomers::contains)
	            .count();

	    return (double) returningCustomers / currentMonthCustomers.size() * 100;
	}
	
	// Longest Order Processing Times
	
	public List<OrderProcessingTime> getLongestProcessingOrders() {
	    List<Orders> deliveredOrders = IOrdersRepository.findDeliveredOrders();
	    
	    return deliveredOrders.stream()
	        .map(order -> new OrderProcessingTime(order.getId(), order.getOrderDate(), order.getDeliveryDate(),
	                ChronoUnit.DAYS.between(order.getOrderDate(), order.getDeliveryDate())))
	        .sorted(Comparator.comparing(OrderProcessingTime::getProcessingTime).reversed())
	        .limit(5)
	        .collect(Collectors.toList());
	}
	
	// Best and Worst Product Categories by AOV
	
	public Categories getCategoryWithHighestAOV() {
	    List<Object[]> results = IOrdersRepository.calculateCategoryAOV();
	    return results.stream()
	        .map(obj -> new CategoryStats((String) obj[0], (Double) obj[1]))
	        .sorted(Comparator.comparing(Categories::getAvgOrderValue).reversed())
	        .findFirst()
	        .orElse(null);
	}
	
	//  Seasonal Order Patterns
	
	public Map<String, Double> getSeasonalRevenue() {
	    List<Orders> orders = IOrdersRepository.findAll();
	    return orders.stream()
	        .collect(Collectors.groupingBy(
	            order -> mapMonthToSeason(order.getOrderDate().getMonthValue()),
	            Collectors.summingDouble(Orders::getTotalAmount)
	        ));
	}

	private String mapMonthToSeason(int month) {
	    return switch (month) {
	        case 12, 1, 2 -> "Winter";
	        case 3, 4, 5 -> "Spring";
	        case 6, 7, 8 -> "Summer";
	        default -> "Fall";
	    };
	}
}
