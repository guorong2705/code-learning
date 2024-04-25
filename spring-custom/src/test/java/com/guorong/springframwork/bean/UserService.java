package com.guorong.springframwork.bean;

public class UserService {

    private String uid;

    private UserDao userDao;

    public UserService() {
    }

    public UserService(String uid) {
        this.uid = uid;
    }

    public void queryUserInfo() {
        System.out.println("查询用户 --->>> " + userDao.getUser(uid));
    }

    public void printUid() {
        System.out.println(String.format("user uid {%s}", uid));
    }
}
