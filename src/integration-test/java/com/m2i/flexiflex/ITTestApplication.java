package com.m2i.flexiflex;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("ittest")
public class ITTestApplication {

    @Autowired
    private DataSource dataSource;

    @Test
    public void should_check_we_use_a_pooled_datasource() {
        assertThat(dataSource.getClass().getName())
                .isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
    }
}
