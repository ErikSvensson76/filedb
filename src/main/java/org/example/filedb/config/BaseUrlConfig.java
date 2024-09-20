package org.example.filedb.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "base")
@Getter
@Validated
public class BaseUrlConfig {

  @NotNull private final String url;

  @ConstructorBinding
  public BaseUrlConfig(String url) {
    this.url = url;
  }
}
