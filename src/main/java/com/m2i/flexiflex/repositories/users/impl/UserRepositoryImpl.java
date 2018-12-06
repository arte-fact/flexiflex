package com.m2i.flexiflex.repositories.users.impl;

import com.m2i.flexiflex.repositories.BaseRepositoryImpl;
import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.repositories.users.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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
    public boolean existsByEmail(String mail) {
        TypedQuery<User> query = (TypedQuery<User>) getEntityManager()
                .createNativeQuery("select * from user where email = :value", User.class)
                .setParameter("value", mail);
        return query.getSingleResult() != null;
    }

    @Override
    public User create(String mail, String password) {
        User user = new User();
        user.setEmail(mail);
        user.setPassword(password);
        getEntityManager().persist(user);

        Query query = getEntityManager()
                .createNativeQuery("select * from user where email = ?", User.class)
                .setParameter(1, mail);

        return (User) query.getSingleResult();
    }
}