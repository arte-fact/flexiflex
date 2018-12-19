package com.m2i.flexiflex.services.user;


import com.m2i.flexiflex.repositories.model.user.User;

public interface UserService {
    User getByMail(String email);

    User getByUUID(String uuid);

    User create(String mail, String password);

    boolean existsByMail(String email);

    boolean existsByUUID(String uuid);

    User update(User user);

    User updateValidationToken(User user);
}

