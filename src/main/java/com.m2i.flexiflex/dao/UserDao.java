package com.m2i.flexiflex.dao;

import com.m2i.flexiflex.model.User;

import java.util.List;

public interface UserDao {
    List<User> list();

    void save(User user);

    User create(String mail, String password);

    User getByMail (String email);

    void deleteUserByMail (String email);

    User update (User user);
}

