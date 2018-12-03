package com.m2i.flexiflex.service;

import com.m2i.flexiflex.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> list();

    User getByMail(String email);

    void deleteUserByMail(String email);

    User create(String mail, String password);

    User update(User user);

}

