package com.m2i.flexiflex.services.user;


import com.m2i.flexiflex.repositories.model.user.User;

import java.util.List;

public interface UserService {
    List<User> list();

    User getByMail(String email);

    User getByUUID(String uuid);

    User create(String mail, String password);

    User update(User user);

    User updateValidationToken(User user);
}

