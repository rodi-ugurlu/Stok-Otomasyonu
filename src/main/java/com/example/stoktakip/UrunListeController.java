package com.example.stoktakip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

import static com.example.stoktakip.HelloApplication.*;

public class UrunListeController {
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
    private Button listeleButonu;
    @FXML
    public Button kaldirButonu;
    @FXML
    public Button geriButonu;

    private ObservableList<Product> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        urunadColumn.setCellValueFactory(new PropertyValueFactory<>("urunad"));
        alisfiyatColumn.setCellValueFactory(new PropertyValueFactory<>("alisfiyat"));
        satisfiyatColumn.setCellValueFactory(new PropertyValueFactory<>("satisfiyat"));
        stokColumn.setCellValueFactory(new PropertyValueFactory<>("stok"));
        myTableView.setItems(items);
        myTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public UrunListeController(){
        listeleButonFonksiyonu();
    }

    @FXML
    public void listeleButonFonksiyonu() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";

        try {
            Connection myConnection = DriverManager.getConnection(url, username, password);
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM uruntablosu");

            items.clear();

            while (myResultSet.next()) {
                int id = myResultSet.getInt("id");
                String urunad = myResultSet.getString("urunadi");
                double alisfiyat = myResultSet.getDouble("alisfiyat");
                double satisfiyat = myResultSet.getDouble("satisfiyat");
                int stok = myResultSet.getInt("stok");

                items.add(new Product(id, urunad, alisfiyat, satisfiyat, stok));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void justComeHome() {
        goBack();
    }
    public void justComeHome2() {
        goBackAdminPanel();
    }
    public void kaldirButonuFonksiyonu() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";

        ObservableList<Product> selectedProducts = myTableView.getSelectionModel().getSelectedItems();

        if (!selectedProducts.isEmpty()) {
            Connection myConnection = null;
            try {
                myConnection = DriverManager.getConnection(url, username, password);
                myConnection.setAutoCommit(false);

                PreparedStatement myPreparedStatement = myConnection.prepareStatement("DELETE FROM uruntablosu WHERE id = ?");

                for (Product product : selectedProducts) {
                    myPreparedStatement.setInt(1, product.getId());
                    myPreparedStatement.addBatch(); // Batch'e ekleyin
                }

                int[] affectedRows = myPreparedStatement.executeBatch();

                myConnection.commit();

                items.removeAll(selectedProducts);

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (myConnection != null) {
                        myConnection.rollback();
                    }
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            } finally {
                try {
                    if (myConnection != null) {
                        myConnection.close();
                    }
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

}




