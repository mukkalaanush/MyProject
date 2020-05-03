package com.project.ordermanagment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductUINMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productId;

    private String productUin;

    private boolean productIsPresent;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUin() {
        return productUin;
    }

    public void setProductUin(String productUin) {
        this.productUin = productUin;
    }

    public boolean isProductIsPresent() {
        return productIsPresent;
    }

    public void setProductIsPresent(boolean productIsPresent) {
        this.productIsPresent = productIsPresent;
    }
}
