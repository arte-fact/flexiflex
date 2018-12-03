package com.m2i.flexiflex.service;

import com.m2i.flexiflex.persistence.UserPersistence;
import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.properties.UserProperties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HibernateTest {
    private final Session session = HibernateSession.getSession();
    private String email = "toto@email.com";
    private String password = "secret";

    @Test
    public void hibernateDontMakeEntityCastError () {
        UserPersistence.delete(email);
        UserEntity userEntity = UserPersistence.create(email, password);

        assert userEntity.getClass() == UserEntity.class;

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                .add(Property.forName(UserProperties.EMAIL).eq(userEntity.getEmail()));
        try {
            UserEntity user = (UserEntity) detachedCriteria.getExecutableCriteria(session).uniqueResult();
            assert userEntity.getClass() == user.getClass();
            assert userEntity.getEmail().equals(user.getEmail());
        } catch (HibernateException e) {
            e.fillInStackTrace();
        }

    }
}
