package com.codegym.service.clothing;

import com.codegym.model.Clothing;
import com.codegym.service.DatabaseInit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothingServiceImpl extends DatabaseInit implements IClothingService {

    String selectAllClothing = "SELECT * FROM clothing ";

    String selectAll = "SELECT cl.id, ca.category_name, ca.status, cl.name, cl.description, cl.picture, cl.price," +
            "cl.origin FROM category as ca INNER JOIN clothing cl on cl" + ".category_id = ca.category_id;";

    String insertClothing = "insert into clothing (name, description, picture, price, origin) values (?, ?, ?, ?, ?)";

    String updateClothing = "update clothing set name = ?, description = ?, picture = ?, price = ?, origin = ? where id = ?";

    String deleteClothing = "delete from clothing where id = ?";

    String findPriceStatement = "select * from clothing cl where cl.price = ?";

    String selectAllWhereStatus = "SELECT cl.id, ca.category_name, ca.status, cl.name, cl.description, cl.picture, cl.price," +
            "cl.origin FROM category as ca INNER JOIN clothing cl on cl.category_id = ca.category_id where ca.status like ?;";

    String selectStatus = "SELECT DISTINCT c.status FROM category c";

    String selectNameByID = "select cl.id, cl.name, cl.description, cl.picture, cl.price, cl.origin, ca.category_name, ca.status\n" +
            "from clothing cl inner join category ca on cl.category_id = ca.category_id where cl.category_id = ?";
    String getSelectAllClothing = "select * from clothing where category=?";

    public ClothingServiceImpl() {
    }

    @Override
    public List<Clothing> findAll() {

        List<Clothing> clothing = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(selectAllClothing)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String picture = resultSet.getString("picture");
                String price = resultSet.getString("price");
                String origin = resultSet.getString("origin");

                clothing.add(new Clothing(id, name, description, picture, price, origin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothing;
    }

    @Override
    public List<Clothing> findAllClothingCategory() {

        List<Clothing> clothingCategory = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String picture = resultSet.getString("picture");
                String price = resultSet.getString("price");
                String origin = resultSet.getString("origin");
                String category = resultSet.getString("category_name");
                String status = resultSet.getString("status");

                clothingCategory.add(new Clothing(name, description, picture, price, origin, category, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothingCategory;
    }

    @Override
    public void insert(Clothing clothing) throws SQLException {

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(insertClothing)
        ) {

            statement.setString(1, clothing.getName());
            statement.setString(2, clothing.getDescription());
            statement.setString(3, clothing.getPicture());
            statement.setString(4, clothing.getPrice());
            statement.setString(5, clothing.getOrigin());

            System.out.println(statement);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Clothing clothing) throws SQLException {
        boolean rowUpdated;

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(updateClothing)
        ) {

            statement.setString(1, clothing.getName());
            statement.setString(2, clothing.getDescription());
            statement.setString(3, clothing.getPicture());
            statement.setString(4, clothing.getPrice());
            statement.setString(5, clothing.getOrigin());

            statement.setInt(6, clothing.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        boolean rowDeleted;

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteClothing)
        ) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
    }

    @Override
    public List<Clothing> findByPrice(String price) throws SQLException {
        List<Clothing> clothingList = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(findPriceStatement);
        ) {

            statement.setString(1, price);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String picture = resultSet.getString("picture");
                String origin = resultSet.getString("origin");

                Clothing clothing = new Clothing(id, name, description, picture, price, origin);
                clothingList.add(clothing);
            }
        }
        return clothingList;
    }

    @Override
    public List<Clothing> findByStatus(String status) {
        List<Clothing> clothingList = new ArrayList<>();
        String findByStatusStatement = "";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("");
        ) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String picture = resultSet.getString(4);
                String price = resultSet.getString(5);
                String origin = resultSet.getString(6);
                String category_name = resultSet.getString(7);

                Clothing clothing = new Clothing(id, name, description, picture, price, origin, category_name, status);
                clothingList.add(clothing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothingList;
    }

    @Override
    public List<Clothing> findAllByStatus(String status) {

        List<Clothing> clothingCategory = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(selectAllWhereStatus);

        ) {
            statement.setString(1, "%" + status + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String picture = resultSet.getString("picture");
                String  price = resultSet.getString("price");
                String origin = resultSet.getString("origin");
                String category = resultSet.getString("category_name");

                clothingCategory.add(new Clothing(id, name, description, picture, price, origin, category, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothingCategory;
    }

    @Override
    public List<String> findAllCategoryStatus() {
        List<String> statuses = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(selectStatus)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String status = resultSet.getString("status");

                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    @Override
    public List<Clothing> findByCategoryID(int id) throws SQLException {

        List<Clothing> clothingList = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(selectNameByID)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int clothing_id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String des = resultSet.getString(3);
                String picture = resultSet.getString(4);
                String price = resultSet.getString(5);
                String origin = resultSet.getString(6);
                String category_name = resultSet.getString(7);
                String status = resultSet.getString(8);

                Clothing clothing = new Clothing(clothing_id, name, des, picture, price, origin, category_name, status);
                clothingList.add(clothing);
            }

        }
        return clothingList;
    }
}
