package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.rawmaterials.RawMaterials;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "villages")
public class Village {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;


  @OneToOne(cascade = CascadeType.ALL)
  private Point point;

  @OneToOne
  RawMaterials rawMaterials;

  public Village(String name, Point point) {
    this.name = name;
    this.point = point;
  }
}
