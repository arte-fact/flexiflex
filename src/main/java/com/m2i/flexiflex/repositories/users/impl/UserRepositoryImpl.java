package com.m2i.flexiflex.repositories.users.impl;

import com.m2i.flexiflex.properties.UserProperties;
import com.m2i.flexiflex.repositories.BaseRepositoryImpl;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.repositories.users.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = (TypedQuery<User>) getEntityManager().createNativeQuery("select user.* from flexiflex.user");
        return query.getResultList();
    }

    @Override
    public void insert(User user) {
        Query namedQuery = getEntityManager().createQuery("");
        namedQuery.executeUpdate();
    }

    @Override
    public User getByEmail(String email) {
        TypedQuery<User> query = (TypedQuery<User>) getEntityManager()
                .createNativeQuery("select * from user where email = ?", User.class)
                .setParameter(1, email);
        return query.getSingleResult();
    }

    @Override
    public User getByUUID(String uuid) {
        return getEntityManager().find(User.class, uuid);
    }

    @Override
    public User update(User user) {
        return getEntityManager().merge(user);
    }

    public User setEmailValidated(User user) {
        getEntityManager().setProperty(UserProperties.EMAIL_VALIDE, true);
        return getByUUID(user.getUuid());
    }

    public User updateValidationToken(User user) {
        getEntityManager().setProperty(UserProperties.VALIDATION_TOKEN, UUID.randomUUID().toString());
        return getByUUID(user.getUuid());
    }

    @Override
    public boolean existsByEmail(String mail) {
        return !getEntityManager().createNamedQuery(UserProperties.NQ_FIND_BY_MAIL, User.class).setParameter(UserProperties.EMAIL, mail).getResultList().isEmpty();
    }
    @Override
    public boolean existsByUUID(String uuid) {
        return !getEntityManager().createNamedQuery(UserProperties.NQ_FIND_BY_UUID, User.class).setParameter(UserProperties.UUID, uuid).getResultList().isEmpty();
    }

    @Override
    public User create(String mail, String password) {
        if (!this.existsByEmail(mail)) {
            User user = new User();
            user.setEmail(mail);
            user.setPassword(password);
            user.setUuid(UUID.randomUUID().toString());
            user.setValidationToken(UUID.randomUUID().toString());
            user.setEmailValidation(false);
            user.setInscriptionDate(Date.valueOf(LocalDate.now()));
            getEntityManager().persist(user);
        }
        User dbUser = getEntityManager().createNamedQuery(UserProperties.NQ_FIND_BY_MAIL, User.class)
                .setParameter(UserProperties.EMAIL, mail)
                .setMaxResults(1)
                .getSingleResult();
        return dbUser;
    }
}