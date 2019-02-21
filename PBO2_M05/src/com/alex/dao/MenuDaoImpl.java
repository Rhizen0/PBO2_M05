/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.dao;

import com.alex.entity.Category;
import com.alex.entity.Menu;
import com.alex.utility.DBUtil;
import com.alex.utility.DaoService;
import com.alex.utility.ViewUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Alexius Surya 1772043
 */
public class MenuDaoImpl implements DaoService<Menu> {

    @Override
    public int addData(Menu menu) {
        int result = 0;
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query
                    = "INSERT INTO menu(id, name, price, recommended, category_id) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, menu.getId());
                ps.setString(2, menu.getName());
                ps.setInt(3, menu.getPrice());
                ps.setBoolean(4, menu.isRecommended());
                ps.setInt(5, menu.getCategory().getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Menu menu) {
        int result = 0;
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query
                    = "INSERT INTO student(id, first_name, last_name, birth_date, department_id) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, menu.getId());
                ps.setString(2, menu.getName());
                ps.setInt(3, menu.getPrice());
                ps.setBoolean(4, menu.isRecommended());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public int deleteData(Menu object) {
        int result = 0;
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query = "DELETE FROM menu WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Menu> showAllData() {
        List<Menu> menu = new ArrayList<>();
        try {
            Connection connection = DBUtil.createMySQLConnection();
            String query
                    = "SELECT m.*, c.id, c.name as cname FROM menu m JOIN category c ON c.id = m.category_id";
            try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("cname"));

                    Menu m = new Menu();
                    m.setId(rs.getInt("id"));
                    m.setName(rs.getString("name"));
                    m.setPrice(rs.getInt("price"));
                    m.setRecommended(rs.getBoolean("recommended"));
                    m.setCategory(category);
                    menu.add(m);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ViewUtil.showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
        }
        return menu;
    }

    @Override
    public Menu getOneData(Menu object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
