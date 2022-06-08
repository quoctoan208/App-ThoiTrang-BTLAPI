package com.example.appbtl_webapi.User;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.adapter.SanPhamAdapter;
import com.example.appbtl_webapi.adapter.TheLoaiSPAdapter;
import com.example.appbtl_webapi.api.APIService;
import com.example.appbtl_webapi.model.SanPham;
import com.example.appbtl_webapi.model.TheLoai;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerView tlSanPhamRecycler, spItemRecycler;
    SanPhamAdapter sanPhamAdapter;
    TheLoaiSPAdapter theLoaiSPAdapter;

    TheLoai tl;
    ArrayList<SanPham> listSanPham = new ArrayList<>();
    ArrayList<TheLoai> listTheLoai = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);
        anhxa();
        loadData();
        //Toast.makeText(this, "Thể loại có "+listTheLoai.size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "sản phẩm có "+listSanPham.size(), Toast.LENGTH_SHORT).show();

    }

    private void loadData() {

        //Lấy dữ liệu sản phẩm
        APIService.apiService.getSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    for (SanPham sanpham : response.body()) {
                        listSanPham.add(sanpham);
                        setSanPhamItemRecycler(listSanPham);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Deo co dl", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //Lấy dữ liệu thể loại sản phẩm
        APIService.apiService.getTheLoai().enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                if (response.body() != null) {
                    for (TheLoai theloai : response.body()) {
                        listTheLoai.add(theloai);
                        setTheLoaiSPRecycler(listTheLoai);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Deo co dl", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTheLoaiSPRecycler(List<TheLoai> theLoaiList){

        tlSanPhamRecycler = findViewById(R.id.tl_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        tlSanPhamRecycler.setLayoutManager(layoutManager);
        theLoaiSPAdapter = new TheLoaiSPAdapter(this, theLoaiList);
        tlSanPhamRecycler.setAdapter(theLoaiSPAdapter);

    }

    private void setSanPhamItemRecycler(List<SanPham> sanPhamList){

        spItemRecycler = findViewById(R.id.sanpham_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        spItemRecycler.setLayoutManager(layoutManager);
        sanPhamAdapter = new SanPhamAdapter(this, sanPhamList);
        spItemRecycler.setAdapter(sanPhamAdapter);
    }



    private void anhxa() {

    }
}
