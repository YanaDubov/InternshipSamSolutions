package com.yana.internship.config;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:flyway.properties")

public class MigrateConfig {
    private final Environment environment;

    public MigrateConfig(Environment environment) {
        this.environment = environment;
    }
    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void flywayMigrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setSchemas(environment.getRequiredProperty("flyway.schemas"));
        flyway.setLocations(environment.getRequiredProperty("flyway.locations"));
        flyway.migrate();
    }
}
