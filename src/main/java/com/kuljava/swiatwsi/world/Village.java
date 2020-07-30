package com.kuljava.swiatwsi.world;

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
@Table(name = "villages")
public class Village {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  private Point location;

  @OneToOne
  private User owner;

  public Village(String name, Point location) {
    this.name = name;
    this.location = location;
  }
}
