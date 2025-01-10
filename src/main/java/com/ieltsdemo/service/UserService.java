package com.ieltsdemo.service;

import com.ieltsdemo.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User findOrCreateUser(String email);
}
