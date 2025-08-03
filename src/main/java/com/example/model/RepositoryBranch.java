package com.example.model;

public record RepositoryBranch(
    String branchName,
    String lastCommitSha
) {}