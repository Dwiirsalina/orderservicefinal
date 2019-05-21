package com.pbkk.orderservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pbkk.orderservice.model.OrderDetail;
import com.pbkk.orderservice.service.OrderDetailService;
import com.pbkk.orderservice.service.OrderService;
import com.pbkk.orderservice.token.TokenRequired;

@RequestMapping("/orders")
@RestControllerAdvice
public class OrderDetailController {
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrderService orderService;
	
//	@TokenRequired
	@ResponseBody
	@GetMapping("/{id}/details")
	public List<OrderDetail> getOrderDetails(
			@RequestHeader("token") String token,
			@PathVariable(name = "id") Integer id){
		return orderDetailService.getOrderDetails(id);
	}
	
//	@TokenRequired
//	@ResponseBody
//	@PostMapping("/{id}/details")
//	@ResponseStatus(HttpStatus.CREATED)
//	public OrderDetail addOrderDetail(
//			@RequestHeader("token") String token,
//			@PathVariable(name = "id") Long id,
//		    @Valid @RequestBody OrderDetail orderDetail
//			){
//		return orderDetailService.createOrderDetail(id, orderDetail);
//	}
//	
////	@TokenRequired
//	@ResponseBody
//	@PutMapping("/{id}/details/{detailId}")
//	public OrderDetail updateOrderDetail(
//			@RequestHeader("token") String token,
//			@PathVariable(name = "id") Long orderId,
//			@PathVariable(name = "detailId") Long orderDetailId,
//			@Valid @RequestBody OrderDetail orderDetail
//			){
//		return orderDetailService.updateOrderDetail(orderDetailId, orderDetail);
//	}
//	
////	@TokenRequired
//	@DeleteMapping("/{id}/details/{detailId}")
//	public ResponseEntity<?> deleteOrderDetail(
//			@PathVariable(name = "id") Long orderId,
//			@PathVariable(name = "detailId") Long orderDetailId
//			){
//		return orderDetailService.deleteOrderDetail(orderDetailId);
//	}
	
}
