package com.guorong.mock.dao;


import com.guorong.mock.model.User;

public interface UserDao {
     User getUser();

     User getById(Integer id);

     int insertUser(User user);

}
