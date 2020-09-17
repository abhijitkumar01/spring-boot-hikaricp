package com.example.springboot.jdbc;

import com.zaxxer.hikari.*;
import org.springframework.context.annotation.*;

import javax.sql.*;

@Configuration
@Profile({"dev", "default"})
public class DevDataSourceConfiguration {
  private static final String SQL_CONNECTION_NAME =
          System.getenv("SQL_JDBC_URL");

  private static final String DB_NAME =
          System.getenv("DB_NAME");

  private static final String DB_USER =
          System.getenv("DB_USER");

  private static final String DB_PASS =
          System.getenv("DB_PASS");

  @Bean(name = "dataSource")
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    // Configure which instance and what database user to connect with.
    config.setJdbcUrl(SQL_CONNECTION_NAME + "/" + DB_NAME);
    config.setUsername(DB_USER);
    config.setPassword(DB_PASS);

    HikariDataSource hikariDataSource = new HikariDataSource(config);
    return hikariDataSource;
  }
}
