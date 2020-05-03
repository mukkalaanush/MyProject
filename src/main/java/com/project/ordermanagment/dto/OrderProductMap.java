package com.project.ordermanagment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderProductMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orderId;

    private String productId;

    private String ProductUIN;

    private int productStatus;

    private int giftStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUIN() {
        return ProductUIN;
    }

    public void setProductUIN(String productUIN) {
        ProductUIN = productUIN;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public int getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(int giftStatus) {
        this.giftStatus = giftStatus;
    }
}
