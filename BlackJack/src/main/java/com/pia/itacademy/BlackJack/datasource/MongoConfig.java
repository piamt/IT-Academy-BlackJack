package com.pia.itacademy.BlackJack.datasource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.pia.itacademy.BlackJack.mongodb.repository")
public class MongoConfig {

}

