package com.example.service;

import com.example.dto.UserRepositoryDto;
import java.util.List;

public interface GithubService {

  List<UserRepositoryDto> getUserRepositories(String username);
}
