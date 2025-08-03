package com.example.dto;

public record GithubBranchDto(String name, Commit commit) {

  public record Commit(String sha) {}
}
