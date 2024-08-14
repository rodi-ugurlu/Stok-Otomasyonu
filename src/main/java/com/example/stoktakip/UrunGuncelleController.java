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

public class UrunGuncelleController {
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
    public Button guncelleButtonu;
    @FXML
    public TextField alisfiyatGuncelleField;
    @FXML
    public TextField satisfiyatGuncelleField;
    @FXML
    public TextField adGuncelleField;
    @FXML
    public TextField stokGuncelleField;
    @FXML
    public Text durumYazi;


    private ObservableList<Product> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        urunadColumn.setCellValueFactory(new PropertyValueFactory<>("urunad"));
        alisfiyatColumn.setCellValueFactory(new PropertyValueFactory<>("alisfiyat"));
        satisfiyatColumn.setCellValueFactory(new PropertyValueFactory<>("satisfiyat"));
        stokColumn.setCellValueFactory(new PropertyValueFactory<>("stok"));
        myTableView.setItems(items);

        myTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadModalInfos();
            }
        });
    }


    public UrunGuncelleController() {
        listButton();
    }

    public void listButton() {
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

    public void urunGuncellemeFonksiyonu() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";
        if (adGuncelleField.getText().isEmpty() || alisfiyatGuncelleField.getText().isEmpty() || satisfiyatGuncelleField.getText().isEmpty()
                || stokGuncelleField.getText().isEmpty()) {
            durumYazi.setText("LÜTFEN TÜM KUTUCUKLARI DOLDURUNUZ");
            return;
        }
        Double alisfiyatInt = Double.parseDouble(alisfiyatGuncelleField.getText());
        Double satisfiyatInt = Double.parseDouble(satisfiyatGuncelleField.getText());
        int stokInt = Integer.parseInt(stokGuncelleField.getText());
        if(alisfiyatInt<0 || satisfiyatInt<0 || stokInt<0){
            durumYazi.setText("LÜTFEN GİRDİĞİNİZ DEĞERLERİ KONTROL EDİNİZ ");
            return;

        }


        Product selectedProduct = myTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            try {
                String yeniad = adGuncelleField.getText();
                double yenialis = Double.parseDouble(alisfiyatGuncelleField.getText());
                double yenisatis = Double.parseDouble(satisfiyatGuncelleField.getText());
                int yenistok = Integer.parseInt(stokGuncelleField.getText());

                Connection myConnection = DriverManager.getConnection(url, username, password);
                String updateSQL = "UPDATE uruntablosu SET urunadi = ?, alisfiyat = ?, satisfiyat = ?, stok = ? WHERE id = ?";
                PreparedStatement myPreparedStatement = myConnection.prepareStatement(updateSQL);

                myPreparedStatement.setString(1, yeniad);
                myPreparedStatement.setDouble(2, yenialis);
                myPreparedStatement.setDouble(3, yenisatis);
                myPreparedStatement.setInt(4, yenistok);
                myPreparedStatement.setInt(5, selectedProduct.getId());

                int affectedRows = myPreparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    selectedProduct.setUrunad(yeniad);
                    selectedProduct.setAlisfiyat(yenialis);
                    selectedProduct.setSatisfiyat(yenisatis);
                    selectedProduct.setStok(yenistok);

                    myTableView.refresh();
                }

                myPreparedStatement.close();
                myConnection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı girin.");
            }
        } else {
            System.out.println("Lütfen güncellemek için bir ürün seçin.");
        }
    }

    public void loadModalInfos() {
        Product selectedModal = myTableView.getSelectionModel().getSelectedItem();
        if (selectedModal != null) {
            adGuncelleField.setText(selectedModal.getUrunad());
            alisfiyatGuncelleField.setText(String.valueOf(selectedModal.getAlisfiyat()));
            satisfiyatGuncelleField.setText(String.valueOf(selectedModal.getSatisfiyat()));
            stokGuncelleField.setText(String.valueOf(selectedModal.getStok()));
        }
    }


}
