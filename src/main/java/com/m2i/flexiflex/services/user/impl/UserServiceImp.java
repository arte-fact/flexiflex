package com.m2i.flexiflex.services.user.impl;

import com.m2i.flexiflex.repositories.model.user.User;
import com.m2i.flexiflex.repositories.users.UserRepository;
import com.m2i.flexiflex.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void save(User user) {
        userRepository.insert(user);
    }

    @Transactional(readOnly = true)
    public List<User> list() {
        return (List<User>) userRepository.getAll();
    }

    @Transactional(readOnly = true)
    public User getByMail(String email) {
        return userRepository.getByEmail(email);
    }

    @Transactional
    public void deleteUserByMail(String email){}

    @Transactional
    public User create(String mail, String password){return userRepository.create(mail, password);}

    @Transactional
    public User update(User user){return user;}

    @Transactional
    public boolean existsByMail(String email){return userRepository.existsByEmail(email);}
}
