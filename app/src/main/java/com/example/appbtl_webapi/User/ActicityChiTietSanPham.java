package com.example.appbtl_webapi.User;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ActicityChiTietSanPham extends AppCompatActivity {
    TextView tvtenSP,tvGiaBan, tvtheLoai, tvMota;
    Button btnThemGioHang;
    ImageView imgHinhAnh;
    SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchitietsanpham);
        anhxa();
        loadDuLieu();
    }

    private void loadDuLieu() {
        if ( sanPham != null) {
            tvtenSP.setText(sanPham.getTenSP());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            tvGiaBan.setText(formatter.format(sanPham.getDonGia()));
            tvMota.setText(sanPham.getMotaSP());
            Picasso.get().load(sanPham.getAnh()).into(imgHinhAnh);
        }
    }

    private void anhxa() {
        tvtenSP = findViewById(R.id.chitiet_tensp);
        tvGiaBan = findViewById(R.id.chitiet_dongia);
        //tvtheLoai = findViewById(R.id.chi);
        tvMota = findViewById(R.id.chitiet_mota);
        btnThemGioHang = findViewById(R.id.btn_chitiet_themgiohang);
        imgHinhAnh = findViewById(R.id.img_chitiet_anhsp);
    }
}
