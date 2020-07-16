package com.kuljava.swiatwsi.rawmaterials.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Wood implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private Long quantity;

    public Wood() {
    }

    @OneToOne(mappedBy = "wood")

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
        return "Wood{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
