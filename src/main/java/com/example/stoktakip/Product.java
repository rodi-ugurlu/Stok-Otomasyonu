package com.example.stoktakip;

public class Product {
    private final int id;
    private String urunad;
    private double alisfiyat;
    private double satisfiyat;
    private int stok;

    public Product(int id, String urunad, double alisfiyat, double satisfiyat, int stok) {
        this.id = id;
        this.urunad = urunad;
        this.alisfiyat = alisfiyat;
        this.satisfiyat = satisfiyat;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public String getUrunad() {
        return urunad;
    }

    public double getAlisfiyat() {
        return alisfiyat;
    }

    public double getSatisfiyat() {
        return satisfiyat;
    }

    public int getStok() {
        return stok;
    }

    public void setAlisfiyat(double alisfiyat) {
        this.alisfiyat = alisfiyat;
    }

    public void setSatisfiyat(double satisfiyat) {
        this.satisfiyat = satisfiyat;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    public void setUrunad(String urunad){
        this.urunad=urunad;
    }
}
