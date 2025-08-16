package br.com.estoque.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConfig {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
      .setConnectTimeout(Duration.ofSeconds(5))
      .requestFactory(() -> {
          var factory = new org.springframework.http.client.SimpleClientHttpRequestFactory();
          factory.setReadTimeout((int) Duration.ofSeconds(10).toMillis());
          return factory;
      })
      .build();
  }
}

