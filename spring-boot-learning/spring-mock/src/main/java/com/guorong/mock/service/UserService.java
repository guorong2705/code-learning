package com.guorong.mock.service;

import com.guorong.mock.model.User;

public interface UserService {
    User getUser();

    User getById(Integer id);

    int insertUser(User user);
}
