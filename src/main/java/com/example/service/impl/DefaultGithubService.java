package com.example.service.impl;

import com.example.dto.github.GithubBranchDto;
import com.example.dto.github.GithubRepoDto;
import com.example.exception.UserNotFoundException;
import com.example.dto.RepositoryBranchDto;
import com.example.dto.UserRepositoryDto;
import com.example.service.GithubService;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultGithubService implements GithubService {

  private final RestClient restClient = RestClient.create();

  public List<UserRepositoryDto> getUserRepositories(String username) {
    try {
      var repos = restClient.get()
          .uri("https://api.github.com/users/{username}/repos", username)
          .retrieve()
          .body(new ParameterizedTypeReference<List<GithubRepoDto>>() {});

      return Optional.ofNullable(repos).orElse(List.of()).stream()
          .filter(repo -> !repo.fork())
          .map(repo -> new UserRepositoryDto(repo.name(), repo.owner().login(), fetchBranches(repo.branches_url())))
          .collect(Collectors.toList());
    } catch (HttpClientErrorException.NotFound ex) {
      throw new UserNotFoundException("User not found");
    }
  }

  private List<RepositoryBranchDto> fetchBranches(String branchesUrl) {
    try {
      var url = branchesUrl.replace("{/branch}", "");
      var branches = restClient.get()
          .uri(url)
          .retrieve()
          .body(new ParameterizedTypeReference<List<GithubBranchDto>>() {});
      return Optional.ofNullable(branches).orElse(List.of()).stream()
          .map(branch -> new RepositoryBranchDto(branch.name(), branch.commit().sha()))
          .collect(Collectors.toList());
    } catch (HttpClientErrorException ex) {
      return List.of();
    }
  }
}