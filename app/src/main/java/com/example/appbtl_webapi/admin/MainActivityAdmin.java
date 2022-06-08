package com.example.appbtl_webapi.admin;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.adapter.SanPhamAdminAdapter;
import com.example.appbtl_webapi.api.APIService;
import com.example.appbtl_webapi.model.SanPham;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivityAdmin extends AppCompatActivity {

    AutoCompleteTextView edt_search;
    ImageButton btn_seach;
    Button add;
    public static ListView lv;
    SanPham sanpham;


    public static ArrayList<SanPham> arr = new ArrayList<>();
    List<String>studentSearch = new ArrayList<>();
    public static ArrayAdapter<SanPham> adap;
    public static SanPhamAdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anhxa();
        GetData();
        onClick();


    }

    private void onClick() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityAdmin.this, AddSanPhamActivity.class);
                startActivity(intent);
            }
        });

        btn_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = edt_search.getText().toString();
                if (key.length() == 0){
                    adapter = new SanPhamAdminAdapter(getApplicationContext(),arr);
                    lv.setAdapter(adapter);
                }else {
                    ArrayList<SanPham> arrSP = new ArrayList<>();
                    for (SanPham sanPham: arr){
                        if (sanPham.getTenSP().contains(key)){
                            arrSP.add(sanPham);
                        }
                    }
                    adapter = new SanPhamAdminAdapter(getApplicationContext(),arrSP);
                    lv.setAdapter(adapter);
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sanpham = arr.get(i);
                Intent intent = new Intent(MainActivityAdmin.this, ChiTietSPActivity.class);
                intent.putExtra("masp", sanpham.getMaSP());
                intent.putExtra("matl", sanpham.getMaTL());
                intent.putExtra("tensp", sanpham.getTenSP());
                intent.putExtra("anhsp", sanpham.getAnh());
                intent.putExtra("dongia", sanpham.getDonGia());
                intent.putExtra("soluong", sanpham.getSoLuong());
                intent.putExtra("mota", sanpham.getMotaSP());
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        lv =findViewById(R.id.lv);
        edt_search= findViewById(R.id.edt_seach);
        btn_seach= findViewById(R.id.btn_seach);
        add = findViewById(R.id.btn_addsanpham);

    }

    public void GetData() {
        arr = new ArrayList<>();
        APIService.apiService.getSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    for (SanPham sanpham : response.body()) {
                        studentSearch.add(sanpham.getTenSP());
                        arr.add(sanpham);
                        adapter = new SanPhamAdminAdapter(getApplicationContext(), arr);
                        adap = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, studentSearch);
                        lv.setAdapter(adapter);
                        edt_search.setAdapter(adap);
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
    }

}