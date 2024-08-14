package com.example.stoktakip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.*;

import static com.example.stoktakip.HelloApplication.*;

public class HelloController {
    @FXML
    public Button goUrunEkle;
    public TextField urunKodField;
    public TextField urunAdField;
    public TextField stokAdetField;
    public TableView myTableView;


    public void UrunEkleButton() {
        goToUrunEkle();
    }
    public void urunTakip(){
        goToUrunTakip();
    }
    public void goUrunListele(){
        goToUrunListeleme();
    }
    public void goUrunSorgula(){
        goUrunSorgulaa();
    }
    public void goGuncelle(){
        goUrunGuncelle();

    }

}