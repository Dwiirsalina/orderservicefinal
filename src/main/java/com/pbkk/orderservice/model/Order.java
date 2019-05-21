package com.pbkk.orderservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
@EntityListeners(AuditingEntityListener.class)


public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name = "customer_id")
	private Integer customer_id;
	
	@Column(name = "deals_id")
	private Integer	deals_id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "delivery_address")
	private String delivery_address;
	
		
	@Size(max = 255)
	@Column(name = "order_note")
	private String order_note;
	
	@NotNull
	@Column(name = "order_status")
	private Integer order_status;
	
	@Column(name = "total_price")
	private Double total_price;
	
	@NotNull
	@Column(name = "restaurant_id")
	private Integer restaurant_id;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_created")
	@CreatedDate
	private Date order_created;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public Integer getDeals_id() {
		return deals_id;
	}
	public void setDeals_id(Integer deals_id) {
		this.deals_id = deals_id;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getOrder_note() {
		return order_note;
	}
	public void setOrder_note(String order_note) {
		this.order_note = order_note;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	public Date getOrder_created() {
		return order_created;
	}
	public void setOrder_created(Date order_created) {
		this.order_created = order_created;
	}
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
}
