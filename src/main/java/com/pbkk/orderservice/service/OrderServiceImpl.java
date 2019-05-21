package com.pbkk.orderservice.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkk.orderservice.exception.ResourceNotFoundException;
import com.pbkk.orderservice.model.Order;
import com.pbkk.orderservice.model.OrderDetail;
import com.pbkk.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	public List<Order> getOrdersByParameter(Integer status, Integer customerId) {
		Stream<Order> orders = orderRepository.findAll().stream();
		if(status != null) {
			orders = orders.filter(order -> order.getOrder_status().intValue() == status.intValue());
		}
		if(customerId != null) {
			orders = orders.filter(order -> order.getCustomer_id().intValue() == customerId.intValue());
		}
		return orders.collect(Collectors.toList());
	}

	@Override
	public Order getOrder(Integer orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Id "+ orderId + " not exist"));
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrderStatus(Integer orderId, Integer status) {
		Order order = orderRepository.findById(orderId).orElse(null);
		if(order != null) {
			order.setOrder_status(status);
			return orderRepository.save(order);
		} else {
			throw new ResourceNotFoundException("Order Id "+ orderId + " not found!");
		}
	}
	
//BUAT HITUNG JUMLAH TOTAL 
//	@Override
//	public void recalculateOrderTotalPrice(Long orderId) {
//		Order order = orderRepository.findById(orderId).orElse(null);
//		if (order != null) {
//			List<OrderDetail> orderDetails = order.getOrderDetails();
//			Double price = 0.0;
//			for ( OrderDetail orderDetail: orderDetails) {
//				price += orderDetail.getSubTotal();
//			}
//			order.setPrice(price);
//			Double totalPrice = price - order.getDeals() + order.getDeliveryCost();
//			order.setTotalPrice(totalPrice);
//			orderRepository.save(order);
//		}
//	}

}
