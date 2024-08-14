package com.example.stoktakip;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.stoktakip.HelloApplication.goBack;

public class UrunEkleController {
    @FXML
    private TextField idField;
    @FXML
    private TextField urunAdField;
    @FXML
    private TextField alisfiyatField;
    @FXML
    private TextField satisfiyatField;
    @FXML
    private TextField stokField;
    @FXML
    public Text situationText;


    @FXML
    public void urunEkleFonksiyonu() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";
        if(urunAdField.getText().isEmpty() ||  alisfiyatField.getText().isEmpty()||
                satisfiyatField.getText().isEmpty()|| stokField.getText().isEmpty()){
            situationText.setText("LÜTFEN TÜM KUTUCUKLARI DOLDURUN");
            return;

        }
        Double alisfiyatInt = Double.parseDouble(alisfiyatField.getText());
        Double satisfiyatInt = Double.parseDouble(satisfiyatField.getText());
        int stokInt = Integer.parseInt(stokField.getText());
        if(alisfiyatInt<0 || satisfiyatInt<0 || stokInt<0){
            situationText.setText("LÜTFEN GİRDİĞİNİZ DEĞERLERİ KONTROL EDİNİZ ");
            return;

        }


        String urunAd = urunAdField.getText();
        double alisfiyat = Double.parseDouble(alisfiyatField.getText());
        double satisfiyat = Double.parseDouble(satisfiyatField.getText());
        int stok = Integer.parseInt(stokField.getText());

        String sql = "INSERT INTO uruntablosu ( urunadi, alisfiyat, satisfiyat, stok) VALUES ( ?, ?, ?, ?)";

        try (Connection myConnection = DriverManager.getConnection(url, username, password);
             PreparedStatement myStatement = myConnection.prepareStatement(sql)) {


            myStatement.setString(1, urunAd);
            myStatement.setDouble(2, alisfiyat);
            myStatement.setDouble(3, satisfiyat);
            myStatement.setInt(4, stok);

            int rowsAffected = myStatement.executeUpdate();
            if (rowsAffected > 0) {
                 situationText.setText("ÜRÜN BAŞARIYLA EKLENDİ");
                urunAdField.clear();
                alisfiyatField.clear();
                satisfiyatField.clear();
                stokField.clear();

            } else {
                situationText.setText("BİRŞEYLER TERS GİTTİ ÜRÜN EKLENEMEDİ");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public void justComeHome() {
        goBack();
    }
}
