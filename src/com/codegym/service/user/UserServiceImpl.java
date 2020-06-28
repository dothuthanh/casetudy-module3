package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.service.DatabaseInit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl extends DatabaseInit implements IUserService {
    String selectUser = "select name from user where email = ? and pass_word = ?;";

    @Override
    public User findUser(String email, String pass) throws SQLException {

        User user = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectUser)) {

            statement.setString(1, email);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                user = new User(name);
            }

        }
        return user;
    }
}
