package com.codegym.service.category;

import com.codegym.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryService {
    public List<Category> findAll();

    public void insert(Category category) throws SQLException;

    public void update(Category category) throws SQLException;

    public void remove(int id) throws SQLException;

    public List<Category> findByStatus(String status) throws SQLException;


}
