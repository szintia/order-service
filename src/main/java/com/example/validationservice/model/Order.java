package com.example.validationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.StringJoiner;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("customer=" + customer)
                .toString();
    }
}
