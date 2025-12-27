package br.com.estoque.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class HttpConfig {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
      .connectTimeout(Duration.ofSeconds(5))
      .requestFactory(() -> {
          var factory = new SimpleClientHttpRequestFactory();
          factory.setReadTimeout((int) Duration.ofSeconds(10).toMillis());
          return factory;
      })
      .build();
  }
}

