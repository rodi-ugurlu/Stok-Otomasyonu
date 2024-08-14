package com.example.stoktakip;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.example.stoktakip.HelloApplication.*;

public class AdminPanelController {
    Logger logger = Logger.getLogger(getClass().getName());
    @FXML
    public Button yeniKullaniciButonu;
    @FXML
    public Button loginButton;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public CheckBox beniHatirlaBox;
    @FXML
    public Text situationText;
    @FXML
    public Text situationText2;


    public  void gotakip1(){
        gokullaniciekle1();
    }

    public void checkInfos() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String dbUsername = "postgres";
        String dbPassword = "Rodibaba@1";
        byte[] safePassword = dbPassword.getBytes();

        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            situationText.setText("LÜTFEN TÜM KUTUCUKLARI DOLDURUN");
        }

        try {
            Connection myConnection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String sql = "SELECT role FROM admins WHERE username = ? AND password = ?";
            PreparedStatement myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setString(1, usernameField.getText());
            myPreparedStatement.setString(2, passwordField.getText());

            ResultSet resultSet = myPreparedStatement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                if ("admin".equals(role)) {
                    goHomePage();

                } else if ("guest".equals(role)) {
                    goToUrunListeleme2();
                }
            } else {
                situationText.setText("KULLANICI ADI VEYA ŞİFRE HATALI");
            }

            myConnection.close();
        } catch (SQLException e) {
            logger.info("veritabanina ulasilamadi");
        }
    }
    public void yeniKullaniciCheckFunc() {
        String url = "jdbc:postgresql://localhost:5432/stoktakip";
        String username = "postgres";
        String password = "Rodibaba@1";


        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            situationText2.setText("LÜTFEN TÜM KUTUCUKLARI DOLDURUN");
            return;
        }

        try {
            Connection myConnection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT COUNT(*) FROM admins WHERE username = ? AND password = ?";
            PreparedStatement myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setString(1, usernameField.getText());
            myPreparedStatement.setString(2, passwordField.getText());

            ResultSet resultSet = myPreparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                gokullaniciekle2();
            } else {
                situationText.setText("KULLANICI ADI VEYA ŞİFRE HATALI");
            }

            myConnection.close();
        } catch (SQLException e) {
logger.info("veritabanina ulasilamadi");        }
    }
    public void justComeHome2() {
        goBackAdminPanel();
    }

}

