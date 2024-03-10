package com.guorong.mock.service;

import com.guorong.mock.dao.UserDao;
import com.guorong.mock.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;


    public User getUser() {
        return userDao.getUser();
    }


    public User getById(Integer id) {
        return userDao.getById(id);
    }

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

}

