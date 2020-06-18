package com.dva.demo.configs;

import com.dva.demo.repository.ParentEntityRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.dva.demo.repository"},
        repositoryBaseClass = ParentEntityRepositoryImpl.class)
public class AppConfig {
}
