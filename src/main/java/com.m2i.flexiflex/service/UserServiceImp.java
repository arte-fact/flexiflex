package com.m2i.flexiflex.service;

import com.m2i.flexiflex.dao.UserDao;
import com.m2i.flexiflex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> list() {
        return userDao.list();
    }

    @Transactional(readOnly = true)
    public User getByMail(String email) {
        return userDao.getByMail(email);
    }

    @Transactional
    public void deleteUserByMail(String email){}

    @Transactional
    public User create(String mail, String password){return userDao.create(mail, password);}

    @Transactional
    public User update(User user){return user;}
}
