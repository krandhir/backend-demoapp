package com.self.learning.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Schema(description = "Investment entity")
@Entity(name = "investment")
@Data
public class Investment {
  @Id
  private String clientId;
  private long amountAdded;
  private String investments;
  private String allottedUnits;
}
