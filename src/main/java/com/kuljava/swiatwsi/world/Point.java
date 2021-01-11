package com.kuljava.swiatwsi.world;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(schema = "swiatwsi")
@Entity
@NoArgsConstructor
public class Point {

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private int x;
  @Column
  private int y;
}
