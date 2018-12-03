package com.m2i.flexiflex.service;

import com.m2i.flexiflex.model.User;
import com.m2i.flexiflex.properties.UserProperties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HibernateTest {


    private final Session session = HibernateSession.getSession();
    private String email = "toto@email.com";
    private String password = "secret";

    @Autowired
    private UserServiceImp userServiceImp;

    @Test
    public void hibernateDontMakeEntityCastError () {
        userServiceImp.deleteUserByMail(email);
        User userEntity = userServiceImp.create(email, password);

        assert userEntity.getClass() == User.class;

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class)
                .add(Property.forName(UserProperties.EMAIL).eq(userEntity.getEmail()));
        try {
            User user = (User) detachedCriteria.getExecutableCriteria(session).uniqueResult();
            assert userEntity.getClass() == user.getClass();
            assert userEntity.getEmail().equals(user.getEmail());
        } catch (HibernateException e) {
            e.fillInStackTrace();
        }

    }
}
