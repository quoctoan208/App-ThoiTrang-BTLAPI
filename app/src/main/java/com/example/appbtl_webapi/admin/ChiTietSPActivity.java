package com.example.appbtl_webapi.admin;



import static com.example.appbtl_webapi.admin.MainActivityAdmin.arr;
import static com.example.appbtl_webapi.admin.MainActivityAdmin.adapter;
import static com.example.appbtl_webapi.admin.MainActivityAdmin.lv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.adapter.SanPhamAdminAdapter;
import com.example.appbtl_webapi.api.APIService;
import com.example.appbtl_webapi.dialog.DialogLoading;
import com.example.appbtl_webapi.model.SanPham;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSPActivity extends AppCompatActivity {
    EditText edt_theloai,edt_tensp, edt_dongia, edt_soluong,edt_mota;
    String mMasp,mTensp,mAnhsp,mMota,mTheloai;
    Float mDongia;
    int mSoluong;
    ImageView imageView;
    SanPham sanpham;
    Button delete, update, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        anhxa();
        loadData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void Update() {
                //Tạo đối tượng
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietSPActivity.this);
                //Thiết lập tiêu đề
                b.setTitle("Xác nhận");
                b.setMessage("UpDate Thông Tin Sản Phẩm?");
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mTheloai = edt_theloai.getText().toString();
                        mTensp = edt_tensp.getText().toString();
                        float mdongia = Float.parseFloat(edt_dongia.getText().toString());
                        int sl = Integer.parseInt(edt_soluong.getText().toString());
                        mMota = edt_mota.getText().toString();
                        if(mTensp.equals("") || mTheloai.equals("") || mAnhsp.equals("") || mdongia == 0 ||
                                sl == 0 || mMota.equals("")){
                            Toast.makeText(ChiTietSPActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            SanPham sp = new SanPham(mMasp,mTensp, mTheloai, mAnhsp,mdongia, sl, mMota);
                            APIService.apiService.updateSanpham(mMasp,sp).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    DialogLoading a = new DialogLoading(ChiTietSPActivity.this);
                                    a.show();
                                    if (response.body() != null) {
                                        Toast.makeText(ChiTietSPActivity.this, "Thành công rồi đấy pé", Toast.LENGTH_LONG).show();
                                        a.dismissDialog();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(ChiTietSPActivity.this, "Không được rồi", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                //Nút Cancel
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                //Tạo dialog
                AlertDialog al = b.create();
                //Hiển thị
                al.show();
            }

    private void Delete() {
                //Tạo đối tượng
                AlertDialog.Builder b = new AlertDialog.Builder(ChiTietSPActivity.this);
                //Thiết lập tiêu đề
                b.setTitle("Xác nhận");
                b.setMessage("Xóa Thông Tin Sản Phẩm?");
                // Nút Ok
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        arr.remove(sanpham);
                        adapter = new SanPhamAdminAdapter(getApplicationContext(), arr);
                        lv.setAdapter(adapter);
                        APIService.apiService.deleteSanpham(mMasp).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.body() != null) {
                                    Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });
                        Intent intent = new Intent(ChiTietSPActivity.this, MainActivityAdmin.class);
                        startActivity(intent);
                        finish();
                    }

                });
                //Nút Cancel
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                //Tạo dialog
                AlertDialog al = b.create();
                //Hiển thị
                al.show();

    }

    private void loadData() {
        mMasp = getIntent().getStringExtra("masp");
        mAnhsp = getIntent().getStringExtra("anhsp");
        mTensp = getIntent().getStringExtra("tensp");
        mTheloai = getIntent().getStringExtra("matl");
        mDongia = getIntent().getFloatExtra("dongia", 0.0F);
        mSoluong = getIntent().getIntExtra("soluong",0);
        mMota = getIntent().getStringExtra("mota");
        edt_tensp.setText(mTensp);
        edt_theloai.setText(mTheloai);
        edt_dongia.setText(mDongia+"");
        edt_soluong.setText(mSoluong+"");
        edt_mota.setText(mMota);
//        Glide.with(this).load(mAnhsp).into(imageView);
        Picasso.get().load(mAnhsp).into(imageView);

    }

    private void anhxa() {
        edt_tensp = findViewById(R.id.edt_tensp);
        edt_dongia = findViewById(R.id.edt_dongia);
        edt_soluong = findViewById(R.id.edt_soluong);
        delete = findViewById(R.id.btn_delete);
        update = findViewById(R.id.btn_update);
        cancel = findViewById(R.id.btn_cancel);
        imageView = findViewById(R.id.img_anhsp);
        edt_mota = findViewById(R.id.edt_motasp);
        edt_theloai = findViewById(R.id.edt_theloai);
    }
}
