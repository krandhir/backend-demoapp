package com.self.learning.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;

@Schema(description = "Instrument entity")
@Entity(name = "instrument")
@Data
public class Instrument {

  @Id
  private String proposalId;
  private List<String> equities;
  private List<String> etf;
}
