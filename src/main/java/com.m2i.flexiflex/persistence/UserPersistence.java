package com.m2i.flexiflex.persistence;

import com.m2i.flexiflex.entity.UserEntity;
import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.service.HibernateSession;
import com.m2i.flexiflex.service.TokenGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import javax.persistence.TransactionRequiredException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



public class UserPersistence {

    private static final Session hbsession = HibernateSession.getSession();

    public static void delete(String email) {
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL).eq(email));
            List userEntity = detachedCriteria.getExecutableCriteria(hbsession).list();
            if (!userEntity.isEmpty()) {
                Transaction tx = hbsession.beginTransaction();
                hbsession.delete(userEntity.get(0));
                tx.commit();
            }
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }
    }

    public static UserEntity create(String email, String password) {

        UserEntity user = new UserEntity();
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserEntity.class)
                    .add(Property.forName(UserProperties.EMAIL).eq(email));
            List userEntity = detachedCriteria.getExecutableCriteria(hbsession).list();

            if (userEntity.isEmpty()) {
                Transaction tx = hbsession.beginTransaction();
                user.setEmail(email);
                user.setPassword(password);
                user.setInscriptionDate(Date.valueOf(LocalDate.now()));
                user.setValidationToken(TokenGenerator.GetTokenSHA256());
                user.setEmailValidation(false);
                user.setUuid(UUID.randomUUID().toString());
                hbsession.save(user);
                tx.commit();
            }
        } catch (TransactionRequiredException e) {
            e.fillInStackTrace();
        }

        return user;
    }

    public static boolean exist(String email) {
        return !DetachedCriteria.forClass(UserEntity.class)
                .add(Property.forName(UserProperties.EMAIL).eq(email))
                .getExecutableCriteria(hbsession)
                .list().isEmpty();
    }

    public static UserEntity getByEmail(String email) {
        return (UserEntity) DetachedCriteria.forClass(UserEntity.class)
                .add(Property.forName(UserProperties.EMAIL).eq(email))
                .getExecutableCriteria(hbsession)
                .uniqueResult();
    }

}
