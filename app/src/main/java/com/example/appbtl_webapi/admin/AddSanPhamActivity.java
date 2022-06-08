package com.example.appbtl_webapi.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.api.APIService;
import com.example.appbtl_webapi.dialog.DialogLoading;
import com.example.appbtl_webapi.model.SanPham;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSanPhamActivity extends AppCompatActivity {
    EditText edt_masp, edt_tensp, edt_tl, edt_urlanh, edt_dongia, edt_sl, edt_mota;
    Button btn_add, btn_cancel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsnpham);
        anhxa();
        onclick();

    }

    private void onclick() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sp = new SanPham(edt_masp.getText().toString(), edt_tl.getText().toString(),
                        edt_tensp.getText().toString(), edt_urlanh.getText().toString(),
                        Float.parseFloat(edt_dongia.getText().toString()),
                        Integer.parseInt(edt_sl.getText().toString()),edt_mota.getText().toString());
                APIService.apiService.addSanPham(sp).enqueue(new Callback<SanPham>() {
                    @Override
                    public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                        DialogLoading a = new DialogLoading(AddSanPhamActivity.this);
                        a.show();
                        if (response.body() != null) {
                            a.dismissDialog();
                            Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddSanPhamActivity.this, MainActivityAdmin.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<SanPham> call, Throwable t) {
                        Toast.makeText(AddSanPhamActivity.this, "Không được rồi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void anhxa() {
        edt_masp = findViewById(R.id.edt_add_masp);
        edt_tensp = findViewById(R.id.edt_add_tensp);
        edt_tl = findViewById(R.id.edt_add_theloai);
        edt_urlanh = findViewById(R.id.edt_add_anh);
        edt_dongia = findViewById(R.id.edt_add_dongia);
        edt_sl = findViewById(R.id.edt_add_soluong);
        edt_mota = findViewById(R.id.edt_add_motasp);

        btn_add = findViewById(R.id.btn_add_sanpham);
        btn_cancel = findViewById(R.id.btn_add_cancel);
    }
}
