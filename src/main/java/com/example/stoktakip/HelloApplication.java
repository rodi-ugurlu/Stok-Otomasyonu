package com.example.stoktakip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage primaryStage;
    public static Scene urunEkleScene;
    public static Scene helloPageScene;
    public static Scene urunTakipScene;
    public static Scene urunListelemeScene;
    public static Scene urunSorgulaScene;
    public static Scene urunGuncelleScene;
    public static Scene adminpanelScene;
    public static Scene kullaniciekleScene1;
    public static Scene kullaniciekleScene2;
    public static   Scene urunListelemeScene2 ;


    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-page.fxml"));
        AnchorPane helloPageRoot = fxmlLoader.load();
        helloPageScene = new Scene(helloPageRoot);

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("urun_ekle.fxml"));
        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("urun_sorgula.fxml"));
        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("urun_listeleme.fxml"));
        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("urun_sorgula.fxml"));
        FXMLLoader fxmlLoader6 = new FXMLLoader(HelloApplication.class.getResource("urun_guncelle.fxml"));
        FXMLLoader fxmlLoader7 = new FXMLLoader(HelloApplication.class.getResource("admin_panel.fxml"));
        FXMLLoader fxmlLoader8 = new FXMLLoader(HelloApplication.class.getResource("kullanici_ekle.fxml"));
        FXMLLoader fxmlLoader9 = new FXMLLoader(HelloApplication.class.getResource("kullanici_ekle2.fxml"));
        FXMLLoader fxmlLoader10 = new FXMLLoader(HelloApplication.class.getResource("urun_listeleme2.fxml"));
        AnchorPane urunEkleRoot = fxmlLoader2.load();
        AnchorPane urunTakipRoot = fxmlLoader3.load();
        AnchorPane urunListelemeRoot = fxmlLoader4.load();
        AnchorPane urunSorgulaRoot = fxmlLoader5.load();
        AnchorPane urunGuncelleRoot = fxmlLoader6.load();
        AnchorPane adminpanelRoot = fxmlLoader7.load();
        AnchorPane kullaniciekleRoot = fxmlLoader8.load();
        AnchorPane kullaniciekle2Root = fxmlLoader9.load();
        AnchorPane urunListeleme2Root = fxmlLoader10.load();

        urunEkleScene = new Scene(urunEkleRoot);
        urunTakipScene = new Scene(urunTakipRoot);
        urunListelemeScene = new Scene(urunListelemeRoot);
        urunListelemeScene2 = new Scene(urunListeleme2Root);
        urunSorgulaScene = new Scene(urunSorgulaRoot);
        urunGuncelleScene = new Scene(urunGuncelleRoot);
        adminpanelScene = new Scene(adminpanelRoot);
        kullaniciekleScene1 = new Scene(kullaniciekleRoot);
        kullaniciekleScene2 = new Scene(kullaniciekle2Root);

        stage.setTitle(" STOK TAKİP OTOMASYONU");
        stage.setScene(adminpanelScene);
        stage.show();
        stage.setResizable(false);


    }

    public static void main(String[] args) {
        launch();

    }

    public static void goToUrunEkle() {
        primaryStage.setScene(urunEkleScene);
    }

    public static void goToUrunTakip() {
        primaryStage.setScene(urunTakipScene);
    }

    public static void goToUrunListeleme() {
        primaryStage.setScene(urunListelemeScene);
    }

    public static void goToUrunListeleme2() {
        primaryStage.setScene(urunListelemeScene2);
    }


    public static void goBack() {
        primaryStage.setScene(helloPageScene);
    }

    public static void goUrunSorgulaa() {
        primaryStage.setScene(urunSorgulaScene);
    }

    public static void goUrunGuncelle() {
        primaryStage.setScene(urunGuncelleScene);

        primaryStage.setY(40);
    }

    public static void goHomePage() {
        primaryStage.setScene(helloPageScene);
    }

    public static void gokullaniciekle1() {
        primaryStage.setScene(kullaniciekleScene1);
    }

    public static void gokullaniciekle2() {
        primaryStage.setScene(kullaniciekleScene2);
    }

    public static void goBackAdminPanel() {
        primaryStage.setScene(adminpanelScene);
    }


}
