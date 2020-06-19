package com.kuljava.swiatwsi.exceptions;

public class VillageWithNameAlreadyExistsException extends RuntimeException {
  public VillageWithNameAlreadyExistsException() {
    super("Village with such name already exists");
  }
}
