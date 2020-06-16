package com.kuljava.swiatwsi.exceptions;

public class VillagesAmountExceededException extends RuntimeException {
    public VillagesAmountExceededException() {
    super("The server is full, cannot create another village");
    }
}
