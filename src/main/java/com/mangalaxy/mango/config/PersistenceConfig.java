package com.mangalaxy.mango.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.mangalaxy.mango.repository")
@EnableJpaAuditing(modifyOnCreate = false)
public class PersistenceConfig {

}