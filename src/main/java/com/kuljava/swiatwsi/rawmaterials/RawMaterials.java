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
@NoArgsConstructor
@ToString
public class RawMaterials implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long woodQuantity;
    private Long clayQuantity;
    private Long ironQuantity;

    @OneToOne(mappedBy = "rawMaterials")
    Village village;
}
