package org.example.filedb;

import org.example.filedb.config.BaseUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(
    value = BaseUrlConfig.class
)
public class FiledbApplication {

  public static void main(String[] args) {
    SpringApplication.run(FiledbApplication.class, args);
  }

}
