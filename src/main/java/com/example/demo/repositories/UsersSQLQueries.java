package com.example.demo.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sql-query.users")
@Getter
@Setter
public class UsersSQLQueries {
    private String getAll, getById, insert, deleteById, updateById;
}
