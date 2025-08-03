package com.example.controller;

import com.example.model.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubControllerIT {

  @LocalServerPort
  int port;

  @Test
  public void shouldReturnRepositoriesForExistingUser() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:" + port + "/users/octocat/repositories";
    ResponseEntity<List<UserRepository>> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<UserRepository>>() {
        }
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isNotEmpty();
    assertThat(response.getBody().get(0).repositoryName()).isNotBlank();
  }
}