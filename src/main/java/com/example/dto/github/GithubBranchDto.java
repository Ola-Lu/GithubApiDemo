package com.example.dto.github;

public record GithubBranchDto(String name, Commit commit) {

  public record Commit(String sha) {}
}
