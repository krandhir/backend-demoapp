package com.self.learning.mapper;

import com.self.learning.dto.UserDTO;
import com.self.learning.model.User;
import org.springframework.stereotype.Component;

/**
 * The type User mapper.
 */
@Component
public class UserMapper {

  /**
   * To entity user.
   *
   * @param userDTO the user dto
   * @return the user
   */
  public User toEntity(UserDTO userDTO) {
    if (userDTO == null) {
      return null;
    }

    User user = new User();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    return user;
  }

  /**
   * To dto user dto.
   *
   * @param user the user
   * @return the user dto
   */
  public UserDTO toDTO(User user) {
    if (user == null) {
      return null;
    }

    UserDTO userDTO = new UserDTO();
    userDTO.setName(user.getName());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }
}
