package com.example.appbtl_webapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.User.ActicityChiTietSanPham;
import com.example.appbtl_webapi.model.SanPham;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ProductViewHolder> {

    Context context;
    List<SanPham> sanPhamList;


    public SanPhamAdapter(Context context, List<SanPham> productsList) {
        this.context = context;
        this.sanPhamList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sanpham_row_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        Picasso.get().load(sanPhamList.get(position).getAnh()).into(holder.mAnhSP);
        holder.mTenSP.setText(sanPhamList.get(position).getTenSP());
        holder.mDonGia.setText((sanPhamList.get(position).getDonGia())+" VNĐ");
        holder.mSoLuong.setText(sanPhamList.get(position).getSoLuong()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ActicityChiTietSanPham.class);
/*
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(holder.prodImage, "image");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
               */ context.startActivity(i/*, activityOptions.toBundle()*/);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView mAnhSP;
        TextView mTenSP, mDonGia, mSoLuong;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            mAnhSP = itemView.findViewById(R.id.prod_anhsp);
            mTenSP = itemView.findViewById(R.id.prod_tensp);
            mSoLuong = itemView.findViewById(R.id.prod_soluong);
            mDonGia = itemView.findViewById(R.id.prod_dongia);


        }
    }

}
