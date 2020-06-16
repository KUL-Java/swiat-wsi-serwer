package com.kuljava.swiatwsi.exceptions;

public class VillageWithNameAlreadyExistsException extends Exception {
  public VillageWithNameAlreadyExistsException() {
    super("Village with such name already exists");
  }
}
