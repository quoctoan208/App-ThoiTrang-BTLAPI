package com.example.appbtl_webapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdminAdapter extends ArrayAdapter<SanPham> {
    List<SanPham> sanphamList;

    public SanPhamAdminAdapter(@NonNull Context context, List<SanPham> sanPhamList) {

        super(context, 0, sanPhamList);
        this.sanphamList = sanPhamList;
    }

    @Override
    public int getCount() {

        return sanphamList.size();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sanpham, null, false);
        }
        SanPham sanpham = sanphamList.get(position);

        ImageView imageView = convertView.findViewById(R.id.img_itemanhsp);
        Picasso.get().load(sanpham.getAnh()).into(imageView);
//        Glide.with(convertView).load(sanpham.getAnhSP()).into(imageView);

        TextView tv_idsp = convertView.findViewById(R.id.tv_idsp);
        TextView tv_tensp = convertView.findViewById(R.id.tv_tensp);
        TextView tv_dongia = convertView.findViewById(R.id.tv_dongiasp);
        tv_idsp.setText("MaSP: "+sanpham.getMaSP());
        tv_tensp.setText("Tên SP: "+sanpham.getTenSP().substring(0,20)+" ...");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tv_dongia.setText("Đơn Giá: "+formatter.format(sanpham.getDonGia())+" VNĐ");
        return convertView;
    }

}
