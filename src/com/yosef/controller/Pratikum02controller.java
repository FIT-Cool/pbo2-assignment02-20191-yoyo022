package com.yosef.controller;

import com.yosef.entity.Category;
import com.yosef.entity.Item;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Pratikum02controller implements Initializable {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCategoryName;
    @FXML
    private MenuButton menuCategory;
    @FXML
    private TableView<Item> tableToko;
    @FXML
    private TableColumn<Item, String> co01;
    @FXML
    private TableColumn<Item, Double> co02;
    @FXML
    private TableColumn<Item, String> co03;

    private ObservableList<Item> items;
    ArrayList<String> mycategories = new ArrayList<String>(); //buat array list object


    public void saveAction(ActionEvent actionEvent) {
        Item t = new Item();
        Category c = new Category();
        t.setName(txtName.getText().trim());
        t.setPrice(Double.parseDouble(txtPrice.getText().trim()));
        c.setName(menuCategory.getClass().getName());
        items.add(t);
    }

    public void resetAction(ActionEvent actionEvent) {
    }

    public void updateAction(ActionEvent actionEvent) {
    }

    public void savecategoryAction(ActionEvent actionEvent) {
        mycategories.add(txtCategoryName.getText());
        menuCategory.setI
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = FXCollections.observableArrayList();
        tableToko.setItems(items);
        co01.setCellValueFactory(data ->{
            Item t = data.getValue();
            return new SimpleStringProperty(t.getName());
        });
        co02.setCellValueFactory(data -> {
            Item t = data.getValue();
            return new SimpleObjectProperty(t.getPrice());
        });
        co03.setCellValueFactory(data ->{
            Item t = data.getValue();
            return new SimpleObjectProperty(t.getCategory());
        });
    }
}
