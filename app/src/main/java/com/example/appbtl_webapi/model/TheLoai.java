package com.example.appbtl_webapi.model;

public class TheLoai {
    String maTl;
    String tenTl;

    public TheLoai(String maTl, String tenTl) {
        this.maTl = maTl;
        this.tenTl = tenTl;
    }

    public String getMaTl() {
        return maTl;
    }

    public void setMaTl(String maTl) {
        this.maTl = maTl;
    }

    public String getTenTl() {
        return tenTl;
    }

    public void setTenTl(String tenTl) {
        this.tenTl = tenTl;
    }
}
