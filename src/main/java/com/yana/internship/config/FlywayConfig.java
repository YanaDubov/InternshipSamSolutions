package com.yana.internship.config;

import com.googlecode.flyway.core.Flyway;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:flyway.properties")

public class FlywayConfig {
    private final Environment environment;

    public FlywayConfig(Environment environment, DataSource dataSource) {
        this.environment = environment;
        this.dataSource = dataSource;
    }

    private final DataSource dataSource;

    @PostConstruct
    public void flywayMigrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations(environment.getRequiredProperty("flyway.locations"));
        flyway.migrate();
    }
}
