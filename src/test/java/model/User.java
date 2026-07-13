package model;

import java.util.Objects;

public final class User {

  private final String username;
  private final String password;

  public User(String username, String password) {
    this.username = Objects.requireNonNull(
        username,
        "Username must not be null"
    );

    this.password = Objects.requireNonNull(
        password,
        "Password must not be null"
    );
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}