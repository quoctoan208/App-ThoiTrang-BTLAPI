package com.example.appbtl_webapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbtl_webapi.R;
import com.example.appbtl_webapi.model.TheLoai;

import java.util.List;

public class TheLoaiSPAdapter extends RecyclerView.Adapter<TheLoaiSPAdapter.ProductViewHolder> {

    Context context;
    List<TheLoai> theLoaiList;

    public TheLoaiSPAdapter(Context context, List<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.theloai_row_item, parent, false);
        // lets create a recyclerview row item layout file
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.tenTL.setText(theLoaiList.get(position).getTenTl());

    }

    @Override
    public int getItemCount() {
        return theLoaiList.size();
    }


    public static final class ProductViewHolder extends RecyclerView.ViewHolder{


        TextView tenTL;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tenTL = itemView.findViewById(R.id.tentheloai);

        }
    }

}
