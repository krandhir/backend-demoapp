package com.self.learning.graphql;

import com.self.learning.model.User;
import com.self.learning.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 * The type User resolver.
 */
@Controller
public class UserResolver {

    @Autowired
    private UserRepository userRepository;

  /**
   * Get users list.
   *
   * @return the list
   */
  @QueryMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

  /**
   * Get user user.
   *
   * @param id the id
   * @return the user
   */
  @QueryMapping
    public User getUser(@Argument Long id){
        return userRepository.findById(id).orElse(null);
    }

  /**
   * Create user user.
   *
   * @param name  the name
   * @param email the email
   * @return the user
   */
  @MutationMapping
    public User createUser(@Argument String name, @Argument String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }
}
