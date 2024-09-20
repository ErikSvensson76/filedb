package org.example.filedb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppResourceNotFoundException extends RuntimeException {
  public AppResourceNotFoundException(String message) {
    super(message);
  }

  public static Supplier<AppResourceNotFoundException> getInstanceSupplier(String message) {
    return () -> new AppResourceNotFoundException(message);
  }
}
