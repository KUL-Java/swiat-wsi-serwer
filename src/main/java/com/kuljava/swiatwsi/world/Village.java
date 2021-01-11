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
  @JoinColumn(name = "username")
  private User username;

  public Village(String name, Point point) {
    this.name = name;
    this.point = point;
  }
}
