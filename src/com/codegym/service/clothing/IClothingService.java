package com.codegym.service.clothing;

import com.codegym.model.Clothing;

import java.sql.SQLException;
import java.util.List;

public interface IClothingService {
    public List<Clothing> findAll();

    public List<Clothing> findAllClothingCategory();

    public void insert(Clothing clothing) throws SQLException;


    public void update(Clothing clothing) throws SQLException;

    public void remove(int id) throws SQLException;

    public List<Clothing> findByPrice(String price) throws SQLException;

    public List<Clothing> findByStatus(String status) throws SQLException;

    public List<Clothing> findAllByStatus(String status);

    public List<String> findAllCategoryStatus();

    //List<Clothing> findByCategoryID(int id, String status) throws SQLException;

    public List<Clothing> findByCategoryID(int id) throws SQLException;

}
