package com.ducthang.reactiveprograming.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Order {

    @Id
    private String id;
    private String customerId;
    private Double price;
    private Double discount;

    public Order(String customerId, Double price, Double discount) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.price = price;
        this.discount = discount;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
