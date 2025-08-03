package com.example.controller;

import com.example.dto.error.ErrorResponseDto;
import com.example.model.UserRepository;
import com.example.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GithubController {

  private final GithubService githubService;

  public GithubController(GithubService githubService) {
    this.githubService = githubService;
  }

  @GetMapping("/{username}/repositories")
  public ResponseEntity<List<UserRepository>> getRepositories(@PathVariable String username) {
    List<UserRepository> repositories = githubService.getUserRepositories(username);
    return ResponseEntity.ok(repositories);
  }
}