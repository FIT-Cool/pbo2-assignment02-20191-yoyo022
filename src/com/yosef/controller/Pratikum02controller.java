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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Pratikum02controller implements Initializable {
    @FXML
    private ComboBox<Category> comboboxCategory;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCategoryName;
    @FXML
    private TableView<Item> tableToko;
    @FXML
    private TableColumn<Item, String> co01;
    @FXML
    private TableColumn<Item, Double> co02;
    @FXML
    private TableColumn<Item, String> co03;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;

    Item t = new Item();
    private ObservableList<Item> items;
    private ObservableList<Category> categories;

    Alert alert = new Alert( Alert.AlertType.ERROR );

    public void resetAction(ActionEvent actionEvent) {
        txtCategoryName.clear();
        txtName.clear();
        txtPrice.clear();
        comboboxCategory.setValue( null );
        btnSave.setDisable( false );
    }

    public void updateAction(ActionEvent actionEvent) {
        btnUpdate.setDisable( true );
        t.setName( txtName.getText().trim() );
        t.setPrice( Double.parseDouble( txtPrice.getText().trim() ) );
        t.setCategory( comboboxCategory.getValue() );
        t.setName( txtName.getText().trim() );
        t.setPrice( Double.parseDouble( txtPrice.getText().trim() ) );
        t.setCategory( comboboxCategory.getValue() );
        tableToko.refresh();
    }

    public void savecategoryAction(ActionEvent actionEvent) {
        Category c = new Category();
        c.setName(txtCategoryName.getText().trim());
        boolean found = false; //cek udah ada atau belum di categories
        if(txtCategoryName.getText().equals("")){
            alert.setTitle( "ERROR" );
            alert.setContentText( "Please fill category name" );
            alert.showAndWait();
        }
        for (Category i:categories){
            if(i.getName().equals( c.getName())){
                found = true;
                alert.setTitle( "ERROR" );
                alert.setContentText( "Duplicate category name" );
                alert.showAndWait();
                break;
            }
        }
        if (!found) {
            categories.add(c);
        }
    }

    public void saveAction(ActionEvent actionEvent) {

        if(txtName.getText().isEmpty() || txtPrice.getText().isEmpty() || comboboxCategory.getValue()==null){
            alert.setTitle( "ERROR" );
            alert.setContentText( "Please fill name/price/category" );
            alert.showAndWait();
        }else {
            t.setName( txtName.getText().trim() );
            t.setPrice( Double.parseDouble( txtPrice.getText().trim() ) );
            t.setCategory( comboboxCategory.getValue() );
            items.add(t);
        }
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        btnUpdate.setDisable( false );
        Item item = tableToko.getSelectionModel().getSelectedItem();
        txtName.setText(item.getName());
        txtPrice.setText( String.valueOf(item.getPrice()));
        comboboxCategory.setValue(item.getCategory());
        btnSave.setDisable( true );

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        comboboxCategory.setItems(categories);
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
