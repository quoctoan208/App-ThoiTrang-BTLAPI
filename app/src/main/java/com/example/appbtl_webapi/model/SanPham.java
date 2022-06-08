package com.example.appbtl_webapi.model;


public class SanPham {

    private String maSP ;
    private String maTL ;
    private String tenSP ;
    private String anh ;
    private Float donGia;
    private Integer soLuong ;
    private String motaSP;

    public SanPham(){}

    public SanPham(String maSP, String maTL, String tenSP, String anh, Float donGia, Integer soLuong, String motaSP) {
        this.maSP = maSP;
        this.maTL = maTL;
        this.tenSP = tenSP;
        this.anh = anh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.motaSP = motaSP;
    }

    public SanPham(String maTL, String tenSP, String anh, Float donGia, Integer soLuong, String motaSP) {
        this.maTL = maTL;
        this.tenSP = tenSP;
        this.anh = anh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.motaSP = motaSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getMotaSP() {
        return motaSP;
    }

    public void setMotaSP(String motaSP) {
        this.motaSP = motaSP;
    }
}
