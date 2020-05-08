package com.project.ordermanagment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    private String orderId;

    private String userId;

    private String addressId;

    private String orderDispatchStatus;

    @Temporal(TemporalType.DATE)
    private Date orderInitiateTime;

    @Temporal(TemporalType.DATE)
    private Date orderDispatchTime;

    public List<OrderProductMap> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductMap> products) {
        this.products = products;
    }

    @Transient
    private List<OrderProductMap> products;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getOrderDispatchStatus() {
        return orderDispatchStatus;
    }

    public void setOrderDispatchStatus(String orderDispatchStatus) {
        this.orderDispatchStatus = orderDispatchStatus;
    }

    public Date getOrderInitiateTime() {
        return orderInitiateTime;
    }

    public void setOrderInitiateTime(Date orderInitiateTime) {
        this.orderInitiateTime = orderInitiateTime;
    }

    public Date getOrderDispatchTime() {
        return orderDispatchTime;
    }

    public void setOrderDispatchTime(Date orderDispatchTime) {
        this.orderDispatchTime = orderDispatchTime;
    }
}
