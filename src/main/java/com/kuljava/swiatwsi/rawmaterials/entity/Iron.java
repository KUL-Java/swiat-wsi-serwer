package com.kuljava.swiatwsi.rawmaterials.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Iron implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    Long id;
    Long quantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Iron{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
