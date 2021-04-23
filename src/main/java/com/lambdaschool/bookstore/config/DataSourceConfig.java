package com.lambdaschool.bookstore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${local.run.db:h2}")
    private String  dbValue;

    @Value("${spring.datasource.url:}")
    private String dbURL;

    @Bean
    public DataSource dataSource() {
        if(dbValue.equalsIgnoreCase("POSTGRESQL")) {
            // Assume its heroku
            System.out.println(dbURL);
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl(dbURL);
            return new HikariDataSource(config);
        } else {
            // Assume we are using H2
            String URLString = "jdbc:h2:mem:testdb";
            String DriverClass = "org.h2.Driver";
            String DBUser = "sa";
            String DBpass = "";

            return DataSourceBuilder.create()
                    .username(DBUser)
                    .password(DBpass)
                    .url(URLString)
                    .driverClassName(DriverClass)
                    .build();
        }
    }
}
