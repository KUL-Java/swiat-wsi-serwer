package com.kuljava.swiatwsi.world;

import lombok.*;

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

  public Village(String name, Point point) {
    this.name = name;
    this.point = point;
  }
}
