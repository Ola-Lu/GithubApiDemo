package com.example.dto;

public record RepositoryBranchDto(
    String branchName,
    String lastCommitSha
) {}