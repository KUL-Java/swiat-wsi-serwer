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

  @Column(name = "name")
  private String name;

  @Column(name = "x")
  private int x;

  @Column(name = "y")
  private int y;

  public Village(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }
}
