package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Clothing;
import com.codegym.service.category.CategoryServiceImpl;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.clothing.ClothingServiceImpl;
import com.codegym.service.clothing.IClothingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ClothingServlet", urlPatterns = "/clothing")
public class ClothingServlet extends HttpServlet {
    private final IClothingService clothingService = new ClothingServiceImpl();
    private final ICategoryService categoryService = new CategoryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            switch (action != null ? action : "") {
                case "createClothing":
                    createClothing(request, response);
                    break;
                case "editClothing":
                    editClothing(request, response);
                    break;
                case "findByPrice":
                    findByPrice(request,response);
                    break;
                default:
                    break;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String price = request.getParameter("price");
        if(price != null){
            try {
                findByPrice(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            switch (action != null ? action : "") {
                case "createClothing":
                    showCreateClothing(request, response);
                    break;
                case "editClothing":
                    showEditClothing(request, response);
                    break;
                case "listClothing":
                    showListClothing(request, response);
                    break;
                case "deleteClothing":
                    deleteClothing(request, response);
                    break;
                case "findByPrice":
                    findByClothingPrice(request, response);
                    break;
                case "findByStatus":
                    findByClothingStatus(request, response);
                    break;
                case "findByCategoryName":
                    findByCategoryName(request, response);
                    break;
                default:
                    listClothingCategory(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void findByClothingStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String status = request.getParameter("status");
        List<Clothing> clothing = this.clothingService.findAllByStatus(status);
        request.setAttribute("clothing", clothing);

        List<String> statuses = this.clothingService.findAllCategoryStatus();
        request.setAttribute("statuses", statuses);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listHome/list_clothing_category.jsp");
        requestDispatcher.forward(request, response);

    }

    private void findByPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String price = request.getParameter("price");
        List<Clothing> list = this.clothingService.findByPrice(price);
        RequestDispatcher requestDispatcher;
        if (list == null){
            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("clothing", list);
            requestDispatcher = request.getRequestDispatcher("listClothing/list_clothing.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void findByClothingPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/list_clothing.jsp");
        requestDispatcher.forward(request, response);
    }


    private void listClothingCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        List<Clothing> clothing = this.clothingService.findAllClothingCategory();
        request.setAttribute("clothing", clothing);
        List<Category> categories = this.categoryService.findAll();
        request.setAttribute("categories",categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listHome/list_clothing_category.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showListClothing(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            IOException, ServletException {

        List<Clothing> clothing = this.clothingService.findAll();
        request.setAttribute("clothing", clothing);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/list_clothing.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createClothing(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String picture = request.getParameter("picture");
        String price = request.getParameter("price");
        String origin = request.getParameter("origin");


        Clothing clothing = new Clothing(name, description, picture, price, origin);
        this.clothingService.insert(clothing);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/create_clothing.jsp");
        request.setAttribute("message", "Tao moi thanh cong");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateClothing(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/create_clothing.jsp");
        requestDispatcher.forward(request, response);
    }


    private void editClothing(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String picture = request.getParameter("picture");
        String price = request.getParameter("price");
        String origin = request.getParameter("origin");

        Clothing clothing = new Clothing(id, name, description, picture, price, origin);
        this.clothingService.update(clothing);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/edit_clothing.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showEditClothing(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/edit_clothing.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteClothing(HttpServletRequest request, HttpServletResponse response) throws SQLException,
            IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        this.clothingService.remove(id);
        List<Clothing> clothing = this.clothingService.findAll();
        request.setAttribute("clothing", clothing);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listClothing/list_clothing.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findByCategoryName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("category_id"));

        List<Clothing> clothing = this.clothingService.findByCategoryID(categoryId);

        request.setAttribute("clothing", clothing);
        List<Category> categories = this.categoryService.findAll();
        request.setAttribute("categories", categories);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listHome/list_clothing_category.jsp");
        requestDispatcher.forward(request,response);
    }
}
