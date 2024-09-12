package com.self.learning.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Schema(description = "Consent entity")
@Entity(name = "consent")
@Data
public class Consent {

  @Id
  private String clientId;
  private long thresholdAmount;
  @JsonProperty
  private boolean isSweepAllowed;
  private String sweepFrequency;// weekly, monthly, TODO: use enum

}
