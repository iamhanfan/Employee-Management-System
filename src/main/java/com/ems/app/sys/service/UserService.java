package com.ems.app.sys.service;

import com.ems.app.sys.model.User;

public interface UserService {
    void registerUser(User user);
    User findByEmail(String email);
}