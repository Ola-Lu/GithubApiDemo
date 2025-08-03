package com.example.service;

import com.example.model.UserRepository;
import java.util.List;

public interface GithubService {

  List<UserRepository> getUserRepositories(String username);
}
