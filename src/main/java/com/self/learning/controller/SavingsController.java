package com.self.learning.controller;

import com.self.learning.model.Consent;
import com.self.learning.model.Instrument;
import com.self.learning.model.Investment;
import com.self.learning.repository.ConsentRepository;
import com.self.learning.repository.InstrumentRepository;
import com.self.learning.repository.InvestmentRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Savings API", description = "API for managing savings")
@Controller
@RequestMapping("/savings")
public class SavingsController {

  @Autowired
  private InvestmentRepository investmentRepository;

  @Autowired
  private InstrumentRepository instrumentRepository;

  @Autowired
  private ConsentRepository consentRepository;

  @GetMapping("/instruments")
  public ResponseEntity<List<Instrument>> getInvestmentOptions() {
    return new ResponseEntity<>(instrumentRepository.findAll(), HttpStatus.OK);
  }

  @PostMapping("/consent")
  public ResponseEntity<Consent> createConsent(@RequestBody Consent consent) {

    consentRepository.save(consent);
    return new ResponseEntity<>(consent, HttpStatus.CREATED);
  }

  @PostMapping("/investment")
  public ResponseEntity<Investment> createInvestment(@RequestBody Investment investment) {
    investmentRepository.save(investment);
    return new ResponseEntity<>(investment, HttpStatus.CREATED);
  }
}
