package com.example.controller;

import com.example.dto.UserRepositoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubControllerIT {

  @LocalServerPort
  int port;

  @Test
  void shouldReturnRepositoriesForExistingUser() {
    RestClient restClient = RestClient.builder().build();
    String url = "http://localhost:" + port + "/users/octocat/repositories";
    var response = restClient.get()
        .uri(url)
        .retrieve()
        .toEntity(new ParameterizedTypeReference<List<UserRepositoryDto>>(){});

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isNotEmpty();
    assertThat(response.getBody().get(0).repositoryName()).isNotBlank();
  }
}