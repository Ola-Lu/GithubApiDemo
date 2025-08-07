package com.example.dto;

import java.util.List;

public record UserRepositoryDto(
    String repositoryName,
    String ownerLogin,
    List<RepositoryBranchDto> branches) {}
