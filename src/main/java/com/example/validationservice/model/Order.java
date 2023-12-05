package com.example.validationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.StringJoiner;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private long customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("customerId=" + customerId)
                .toString();
    }
}
