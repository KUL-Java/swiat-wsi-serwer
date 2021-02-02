package com.kuljava.swiatwsi.security;

import com.kuljava.swiatwsi.world.Village;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "swiatwsi")
@Getter
@Setter
public class User {

  @Id
  private String userName;
  private String email;
  private String password;
  private String role;
}
