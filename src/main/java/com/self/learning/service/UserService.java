package com.self.learning.service;

import com.self.learning.model.User;
import com.self.learning.repository.UserRepository;
import jakarta.jms.Queue;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JmsTemplate jmsTemplate;

  @Autowired
  private Queue queue;

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
    User createdUser = userRepository.save(user);
    sendMessage(createdUser);
    return createdUser;
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

  private void sendMessage(User user) {
    try {
      jmsTemplate.convertAndSend(queue, user);
      logger.info("Message sent: {}", user);
    } catch (JmsException e) {
      throw new RuntimeException("Exception occurred while sending the message: {}" +
          e.getMessage());
    }
  }

}
