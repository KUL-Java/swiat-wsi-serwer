package com.kuljava.swiatwsi.rawmaterials;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "swiatwsi")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
