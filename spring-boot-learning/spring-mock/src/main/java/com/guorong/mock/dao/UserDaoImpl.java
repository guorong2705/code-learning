package com.guorong.mock.dao;

import com.guorong.mock.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }
}
