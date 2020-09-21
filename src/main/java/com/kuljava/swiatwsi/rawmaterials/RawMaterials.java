package com.kuljava.swiatwsi.rawmaterials;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class RawMaterials implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long woodQuantity;
    private Long clayQuantity;
    private Long ironQuantity;

    public RawMaterials(Long woodQuantity, Long clayQuantity, Long ironQuantity) {
        this.woodQuantity = woodQuantity;
        this.clayQuantity = clayQuantity;
        this.ironQuantity = ironQuantity;
    }
}
