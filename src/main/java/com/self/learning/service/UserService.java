package com.self.learning.service;

import com.self.learning.model.User;
import com.self.learning.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Find user by id user.
   *
   * @param id the id
   * @return the user
   */
  public User findUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  /**
   * Find all Users.
   *
   * @return the list
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }

  /**
   * Save user user.
   *
   * @param user the user
   * @return the user
   */
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * Exists boolean.
   *
   * @param user the user
   * @return the boolean
   */
  public boolean exists(User user) {
    return userRepository.exists(Example.of(user));
  }
}
