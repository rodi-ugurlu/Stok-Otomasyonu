package com.example.stoktakip;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.stoktakip.HelloApplication.goBack;
import static com.example.stoktakip.HelloApplication.goBackAdminPanel;

public class YeniKullaniciEkleController {
    @FXML
    public TextField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Button kullaniciekleButton;
    @FXML
    public Text sonucText;
    public void kullaniciEkle(){
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";
        try {

            Connection myConnection = DriverManager.getConnection(url,username,password);
            PreparedStatement myPreparedStatement = myConnection.prepareStatement("INSERT INTO admins (username,password) VALUES (?,?)");
            myPreparedStatement.setString(1,usernameField.getText());
            myPreparedStatement.setString(2,passwordField.getText());
            int islemSonuc=myPreparedStatement.executeUpdate();
            if(islemSonuc>0){
                sonucText.setText("KULLANICI BAŞARIYLA EKLENDİ");
                usernameField.clear();
                passwordField.clear();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void justComeHome2() {
        goBackAdminPanel();
    }



}
