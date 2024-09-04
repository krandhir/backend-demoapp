package com.self.learning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The type User dto.
 */
@Data
public class UserDTO {

  @NotBlank(message = "Name is mandatory")
  @Size(message = "Name should not exceed 100 characters", min = 1, max = 100)
  private String name;

  @NotBlank(message = "Email id is mandatory")
  @Email(message = "Invalid email id")
  private String email;

  @Override
  public String toString() {
    return "name: " + name +
        ", email: " + email;
  }

}
