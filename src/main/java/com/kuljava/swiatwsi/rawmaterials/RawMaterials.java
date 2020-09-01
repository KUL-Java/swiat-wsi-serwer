package com.kuljava.swiatwsi.rawmaterials;

import com.kuljava.swiatwsi.world.Village;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "raw_materials")
@Getter
@Setter
@ToString
public class RawMaterials implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "wood_quantity")
    private Long woodQuantity;
    @Column(name = "clay_quantity")
    private Long clayQuantity;
    @Column(name = "iron_quantity")
    private Long ironQuantity;

    public RawMaterials() {
        this.woodQuantity = 0l;
        this.clayQuantity = 0l;
        this.ironQuantity = 0l;
    }
}
