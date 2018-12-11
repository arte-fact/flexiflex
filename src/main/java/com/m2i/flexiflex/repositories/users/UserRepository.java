package com.m2i.flexiflex.repositories.users;

import com.m2i.flexiflex.repositories.BaseRepository;
import com.m2i.flexiflex.repositories.model.user.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends BaseRepository<User, Long> {
    List getAll();

    void insert(User user);

    User getByEmail(String email);
    User getByUUID(String uuid);

    User create(String email, String password);
    User update(User user);

    User updateValidationToken(User user);

    User setEmailValidated(User user);

    boolean existsByEmail(String email);
    boolean existsByUUID(String uuid);
}
