package com.pbkk.orderservice.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.pbkk.orderservice.model.Order;
import com.pbkk.orderservice.model.OrderDetail;
import com.pbkk.orderservice.service.OrderDetailService;
import com.pbkk.orderservice.service.OrderService;
import com.pbkk.orderservice.token.TokenRequired;

@RequestMapping("/orders")
@RestControllerAdvice
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@ResponseBody
	@GetMapping("")
	public List<Order> getAllOrder(
//			@RequestHeader("token") String token,
			@RequestParam(name = "order_status", required = false) String order_status,
			@RequestParam(name = "customer_id", required = false) Integer customer_id
			){
		if (order_status != null) {
			if (order_status.equals("ongoing")) {
				List<Order> ongoing = orderService.getOrdersByParameter(1,customer_id);
//				ongoing.addAll(orderService.getOrdersByParameter(2,customer_id));
//				ongoing.addAll(orderService.getOrdersByParameter(3,customer_id));
				return ongoing;
			}
			if (order_status.equals("completed")) {
				return orderService.getOrdersByParameter(4,customer_id);
			}
			if (order_status.equals("cancelled")) {
				return orderService.getOrdersByParameter(9,customer_id);
			}
			return null;
		} else if(customer_id != null){
			return orderService.getOrdersByParameter(null,customer_id);
		}
		return orderService.getAllOrders();
	}
	
	
	@ResponseBody
	@PostMapping ("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Order createOrder(
//			@RequestHeader("token") String token,
			@Valid @RequestBody Order orderRequest
			) {
		Order newOrder = new Order();
		newOrder.setCustomer_id(orderRequest.getCustomer_id());
		newOrder.setDeals_id(orderRequest.getDeals_id());
		newOrder.setDelivery_address(orderRequest.getDelivery_address());
		newOrder.setOrder_note(orderRequest.getOrder_note());
		newOrder.setOrder_status(orderRequest.getOrder_status());
		newOrder.setTotal_price(orderRequest.getTotal_price());
		newOrder.setRestaurant_id(orderRequest.getRestaurant_id());
//		newOrder.setPrice(orderRequest.getPrice());
		
		
		Order fixOrder = orderService.createOrder(newOrder);
		
		for(OrderDetail orderDetail: orderRequest.getOrderDetails()) {
			OrderDetail newOrderDetail = new OrderDetail();
			newOrderDetail.setMenu_id(orderDetail.getMenu_id());
			newOrderDetail.setOrder_price(orderDetail.getOrder_price());
			newOrderDetail.setOrder_amount(orderDetail.getOrder_amount());
//			newOrderDetail.setSubTotal(orderDetail.getSubTotal());
			newOrderDetail.setOrder(newOrder);
			newOrder.getOrderDetails().add(newOrderDetail);
//			orderDetailService.createOrderDetail(newOrderDetail);
		}
				
		return fixOrder;
	}
	
//	@TokenRequired
	@ResponseBody
	@GetMapping("{id}")
	public Order getOrder(
//			@RequestHeader("token") String token,
			@PathVariable("id") Integer order_id) {
		return orderService.getOrder(order_id);
	}
	
//	@TokenRequired
	@ResponseBody
	@PatchMapping("/{id}/status")
	public Order updateOrderStatus(
//			@RequestHeader("token") String token,
			@PathVariable(name = "id") Integer order_id,
			@RequestParam(name = "status") Integer order_status
			) {
		return orderService.updateOrderStatus(order_id, order_status);
	}
	
}
