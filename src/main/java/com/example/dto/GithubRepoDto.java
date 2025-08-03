package com.example.dto;

public record GithubRepoDto(String name, Owner owner, boolean fork, String branches_url) {

  public record Owner(String login) {}
}
