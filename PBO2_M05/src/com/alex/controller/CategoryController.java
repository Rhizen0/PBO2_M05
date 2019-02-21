/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.controller;

import com.alex.entity.Category;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Alexius Surya 1772043
 */
public class CategoryController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private Button Save;
    @FXML
    private TableColumn<Category, String> col01;
    @FXML
    private TableColumn<Category, String> col02;
    @FXML
    private TableView<Category> tbCategory;

    private MainFormController mainController;

    public void setMainController(MainFormController mainController) {
        this.mainController = mainController;
        tbCategory.setItems(mainController.getCategories());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        col01.setCellValueFactory((data) -> {
            Category c = data.getValue();
            return new SimpleStringProperty(String.valueOf(c.getId()));
        });
        col02.setCellValueFactory((data) -> {
            Category c = data.getValue();
            return new SimpleStringProperty(c.getName());
        });
    }

    @FXML
    private void saveAction(ActionEvent event) {
        Category c = new Category();
        c.setId(Integer.valueOf(txtId.getText().trim()));
        c.setName(txtName.getText().trim());
        mainController.getCategoryDao().addData(c);
        mainController.getCategories().add(c);
    }

}
