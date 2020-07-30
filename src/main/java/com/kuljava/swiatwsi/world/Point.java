package com.kuljava.swiatwsi.world;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table
@Entity
@NoArgsConstructor
public class Point {

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private int x;

  @Column
  private int y;
}
