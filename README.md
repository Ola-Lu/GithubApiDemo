# GithubApiDemo

## 📘 Description

This application provides a REST API endpoint to retrieve a GitHub user's public repositories (excluding forks), along with their branches and the SHA of the last commit in each branch.

---

## 🛠️ Technologies

- Java 21
- Spring Boot 3.5.0
- Maven
- REST API

---

## ▶️ How to Run

1. Build the project:
   ```bash
   mvn clean install
   ```

2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

3. By default, the application runs on port `8080`.

---

## 📡 Usage

### Endpoint:
```
GET /users/{username}/repositories
```

### Example:
```
GET http://localhost:8080/users/octocat/repositories
```

### Sample Response:

```json
[
  {
    "repositoryName": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "e5bd3914e2e596debea16f433f57875b5b90bcd6"
      }
    ]
  }
]
```

---

## ✅ Tests

To run tests:
  ```bash
  mvn test
  ```

---

## 📝 Notes

- The application uses the public GitHub API.
- Forked repositories are automatically filtered out and will not appear in the results.