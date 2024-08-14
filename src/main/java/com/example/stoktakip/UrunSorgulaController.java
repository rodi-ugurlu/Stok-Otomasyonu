package com.example.stoktakip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.*;

import static com.example.stoktakip.HelloApplication.goBack;

public class UrunSorgulaController {
    @FXML
    private TableView<Product> myTableView;

    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> urunadColumn;
    @FXML
    private TableColumn<Product, Double> alisfiyatColumn;
    @FXML
    private TableColumn<Product, Double> satisfiyatColumn;
    @FXML
    private TableColumn<Product, Integer> stokColumn;
    @FXML
    public Button sorgulaButonu;
    @FXML
    public TextField sorguField;
    @FXML
    public Text errText;
    private ObservableList<Product> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        urunadColumn.setCellValueFactory(new PropertyValueFactory<>("urunad"));
        alisfiyatColumn.setCellValueFactory(new PropertyValueFactory<>("alisfiyat"));
        satisfiyatColumn.setCellValueFactory(new PropertyValueFactory<>("satisfiyat"));
        stokColumn.setCellValueFactory(new PropertyValueFactory<>("stok"));
        myTableView.setItems(items);
    }

    @FXML
    public void urunSorgulaFonksiyonu() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";
        String sorgu = sorguField.getText();
        if(sorgu.isEmpty()){
            errText.setText("LÜTFEN KUTUCUĞU DOLDURUNUZ");
            return;
        }

        try {
            Connection myConnection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM uruntablosu WHERE urunadi ILIKE  ? ";
            PreparedStatement myStatement = myConnection.prepareStatement(sql);
            myStatement.setString(1, "%"+sorgu+"%");

            ResultSet myResultSet = myStatement.executeQuery();

            items.clear();

            while (myResultSet.next()) {
                int id = myResultSet.getInt("id");
                String urunad = myResultSet.getString("urunadi");
                double alisfiyat = myResultSet.getDouble("alisfiyat");
                double satisfiyat = myResultSet.getDouble("satisfiyat");
                int stok = myResultSet.getInt("stok");

                items.add(new Product(id, urunad, alisfiyat, satisfiyat, stok));
                sorguField.clear();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void justComeHome() {
        goBack();
    }

}
