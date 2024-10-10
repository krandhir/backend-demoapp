package com.self.learning.config;

import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {

  @Bean
  public Queue queue() {
    return (Queue) new ActiveMQQueue("user-queue");
  }
}
