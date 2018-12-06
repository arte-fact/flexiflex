package com.m2i.flexiflex.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
