package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.service.DatabaseInit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl extends DatabaseInit implements ICategoryService {

    String selectAllCategory = "SELECT * FROM category ";
    String insertCategory= "insert into category (name, status) value (?,?)";
    String updateCategory = "update category set name = ?, status = ? where id = ?";
    String deleteCategory = "delete from category where id = ?";
    String query = "select * from category where status like ?";

    @Override
    public List<Category> findAll() {

        List<Category> categories = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(selectAllCategory)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");

                categories.add(new Category(id, name, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void insert(Category category) throws SQLException {

        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(insertCategory)
        ) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getStatus());
            System.out.println(statement);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Category category) throws SQLException {
        boolean rowUpdated;

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(updateCategory)
        ) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getStatus());
            statement.setInt(3, category.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        boolean rowDeleted;

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteCategory)
        ) {

            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
    }

    @Override
    public List<Category> findByStatus(String status) throws SQLException {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + status + "%");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String category_name = resultSet.getString(2);
                Category category = new Category(id, category_name, status);
                categories.add(category);
            }
        }
        return categories;
    }
}
