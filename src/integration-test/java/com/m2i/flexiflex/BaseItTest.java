package com.m2i.flexiflex;

import com.m2i.flexiflex.repositories.users.impl.UserRepositoryImpl;
import com.m2i.flexiflex.services.user.impl.UserServiceImp;
import lombok.Data;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManagerFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles("ittest")
@DataJpaTest
@AutoConfigureMockRestServiceServer
public abstract class BaseItTest {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @MockBean
    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @MockBean
    @Autowired
    public UserServiceImp userServiceImp;
}
