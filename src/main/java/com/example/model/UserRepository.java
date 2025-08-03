package com.example.model;

import java.util.List;

public record UserRepository(
    String repositoryName,
    String ownerLogin,
    List<RepositoryBranch> branches) {}
