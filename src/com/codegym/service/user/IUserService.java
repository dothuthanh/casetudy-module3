package com.codegym.service.user;

import com.codegym.model.User;

import java.sql.SQLException;

public interface IUserService {
    public User findUser (String email, String pass) throws SQLException;
}
