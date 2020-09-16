package com.example.springboot.jdbc;

import com.zaxxer.hikari.*;
import org.springframework.context.annotation.*;

import javax.sql.*;

@Configuration
@Profile({"gcp", "default"})
public class GCPDataSourceConfiguration {
  private static final String CLOUD_SQL_CONNECTION_NAME =
          System.getenv("CLOUD_SQL_CONNECTION_NAME");

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
    config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
    config.setUsername(DB_USER); // e.g. "root", "postgres"
    config.setPassword(DB_PASS); // e.g. "my-password"
    config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
    config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);

    HikariDataSource hikariDataSource = new HikariDataSource(config);
    return hikariDataSource;
  }
}

