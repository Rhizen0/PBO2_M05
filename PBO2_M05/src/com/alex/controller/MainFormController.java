/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.controller;

import com.alex.MainApp;
import com.alex.dao.CategoryDaoImpl;
import com.alex.dao.MenuDaoImpl;
import com.alex.entity.Category;
import com.alex.entity.Menu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alexius Surya 1772043
 */
public class MainFormController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDescription;
    @FXML
    private CheckBox checkBoxRecommended;
    @FXML
    private TableColumn<Menu, String> col01;
    @FXML
    private TableColumn<Menu, String> col02;
    @FXML
    private TableColumn<Menu, String> col03;
    @FXML
    private TableColumn<Menu, String> col04;
    @FXML
    private TableView<Menu> tbMenu;
    @FXML
    private TableColumn<Menu, String> col05;
    @FXML
    private ComboBox<Category> cbCategory;

    private Stage secondStage;

    private CategoryDaoImpl categoryDao;
    @FXML
    private BorderPane borderPane;
    private Menu selectedMenu;
    private Category selectedCateg;
    @FXML
    private Button saveId;
    @FXML
    private Button resetId;
    @FXML
    private Button updateId;
    @FXML
    private Button deleteId;
    private MainFormController mainController;

    public CategoryDaoImpl getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = new CategoryDaoImpl();
        }
        return categoryDao;
    }

    private ObservableList<Category> categories;

    public ObservableList<Category> getCategories() {
        if (categories == null) {
            categories = FXCollections.observableArrayList();
            categories.addAll(getCategoryDao().showAllData());
        }
        return categories;
    }

    private MenuDaoImpl menuDao;

    public MenuDaoImpl getMenuDao() {
        if (menuDao == null) {
            menuDao = new MenuDaoImpl();
        }
        return menuDao;
    }

    private ObservableList<Menu> menus;

    public ObservableList<Menu> getMenus() {
        if (menus == null) {
            menus = FXCollections.observableArrayList();
            menus.addAll(getMenuDao().showAllData());
        }
        return menus;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tbMenu.setItems(getMenus());
        cbCategory.setItems(getCategories());;

        col01.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(String.valueOf(m.getId()));
        });
        col02.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(m.getName());
        });
        col03.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(String.valueOf(m.getPrice()));
        });
        col04.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(String.valueOf(m.isRecommended()));
        });
        col05.setCellValueFactory((data) -> {
            Menu m = data.getValue();
            return new SimpleStringProperty(m.getCategory().getName());
        });
    }

    @FXML
    private void saveActionBtn(ActionEvent event) {
        Menu menu = new Menu();
        menu.setId(Integer.valueOf(txtId.getText().trim()));
        menu.setName(txtName.getText().trim());
        menu.setPrice(Integer.valueOf(txtPrice.getText().trim()));
        menu.setRecommended(checkBoxRecommended.isSelected());
        menu.setCategory(cbCategory.getValue());
        getMenus().add(menu);
        tbMenu.refresh();
        resetId.setDisable(false);
        updateId.setDisable(false);
        deleteId.setDisable(false);

    }

    @FXML
    private void resetActionBtn(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtDescription.clear();
        cbCategory.setValue(null);
        checkBoxRecommended.setSelected(false);
        selectedMenu = null;
        saveId.setDisable(false);
        resetId.setDisable(true);
        updateId.setDisable(true);
        deleteId.setDisable(true);
    }

    @FXML
    private void updateActionBtn(ActionEvent event) {
        selectedMenu.setId(Integer.valueOf(txtId.getText().trim()));
        selectedMenu.setName(txtName.getText().trim());
        selectedMenu.setPrice(Integer.valueOf(txtPrice.getText().trim()));
        selectedMenu.setRecommended(checkBoxRecommended.isSelected());
        selectedMenu.setCategory(cbCategory.getValue());
        getMenus().add(selectedMenu);
        tbMenu.refresh();
    }

    @FXML
    private void deleteActionBtn(ActionEvent event) {
        Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        deleteConfirmation.setContentText("Are you sure want to delete?");
        deleteConfirmation.showAndWait();
        if (deleteConfirmation.getResult() == ButtonType.YES) {
            mainController.getCategoryDao().deleteData(selectedCateg);
            mainController.getCategories().clear();
            mainController.getCategories().addAll(getCategoryDao().showAllData());
        }
    }

    @FXML
    private void editCategory(ActionEvent event
    ) {
        try {
            secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Category.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            CategoryController controller = loader.getController();
            controller.setMainController(this);
            secondStage.initOwner(borderPane.getScene().getWindow());
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.setScene(scene);
            secondStage.setTitle("PBO2 - P04");
            secondStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tbClicked(MouseEvent event
    ) {
        selectedMenu = tbMenu.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            txtId.setText(String.valueOf(selectedMenu.getId()));
            txtName.setText(selectedMenu.getName());
            txtPrice.setText(String.valueOf(selectedMenu.getPrice()));
            checkBoxRecommended.isSelected();
            cbCategory.setValue(selectedMenu.getCategory());
            saveId.setDisable(true);
            resetId.setDisable(true);
            deleteId.setDisable(false);
            updateId.setDisable(false);
        }
    }

}
