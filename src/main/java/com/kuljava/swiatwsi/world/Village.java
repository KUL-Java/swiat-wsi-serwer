package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.rawmaterials.RawMaterials;
import com.kuljava.swiatwsi.security.User;
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
@Table(name = "village", schema = "swiatwsi")
public class Village {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;


  @OneToOne(cascade = CascadeType.ALL)
  private Point point;

  @OneToOne(cascade = CascadeType.ALL)
  RawMaterials rawMaterials;

  @OneToOne
  private User user;

  public Village(String name, Point point, RawMaterials rawMaterials, User user) {
    this.name = name;
    this.point = point;
    this.rawMaterials = rawMaterials;
    this.user = user;
  }
}
