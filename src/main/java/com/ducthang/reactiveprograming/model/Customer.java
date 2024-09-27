package com.ducthang.reactiveprograming.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Customer {
    @Id
    private String id;
    private String name;
    private String job;

    public Customer(String name, String job) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Customer{}";
    }
}
