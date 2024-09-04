package com.self.learning.controller;

import com.self.learning.dto.UserDTO;
import com.self.learning.exception.UserNotFoundException;
import com.self.learning.mapper.UserMapper;
import com.self.learning.model.User;
import com.self.learning.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 */
@Tag(name = "User API", description = "API for managing users")
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserMapper userMapper;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  /**
   * Gets all users.
   *
   * @return the all users
   */
  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<User> users = userRepository.findAll();
    List<UserDTO> userDTOs = users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    return new ResponseEntity<>(userDTOs, HttpStatus.OK);
  }

  /**
   * Create user response entity.
   *
   * @param userDTO the user dto
   * @return the response entity
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createUser(@Validated @RequestBody UserDTO userDTO) {
    logger.info("User details: {}", userDTO);
    User user = userMapper.toEntity(userDTO);
    if (!userRepository.exists(Example.of(user))) {
      User addedUser = userRepository.save(user);
      UserDTO addedUserDTO = userMapper.toDTO(addedUser);
      logger.info("User created successfully: {}", addedUserDTO);
      return new ResponseEntity<>(addedUserDTO, HttpStatus.CREATED);
    } else {
      logger.info("User exists already.");
      return new ResponseEntity<>("{\"error\":\"User exists already\"}", HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Gets user by id.
   *
   * @param id the id
   * @return the user by id
   * @throws UserNotFoundException the user not found exception
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
    //return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    UserDTO userDTO = userMapper.toDTO(user);
    return ResponseEntity.ok(userDTO);
  }
}
