package com.m2i.flexiflex.dao;

import com.m2i.flexiflex.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> list() {
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getByMail(String email) {
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where User.email = email");
        return ((Query<User>) query).uniqueResult();
    }

    @Override
    public void deleteUserByMail (String email) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("delete from User where User.email = email");
        query.executeUpdate();
    }

    @Override
    public User create(String mail, String password) {
        User user = new User();
        user.setEmail(mail);
        user.setPassword(password);
        sessionFactory.getCurrentSession().save(user);

        return user;
    }

    @Override
    public User update (User user) {
        sessionFactory.getCurrentSession().update(user);

        return user;
    }
}
