package com.example.appbtl_webapi.api;

import com.example.appbtl_webapi.model.SanPham;
import com.example.appbtl_webapi.model.TheLoai;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    Gson gson = new GsonBuilder().setLenient().setDateFormat("dd/MM/yyyy").create();
    APIService apiService = new Retrofit.Builder().baseUrl("https://btl-doan-severapi.conveyor.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("api/SANPHAMs")
    Call<List<SanPham>>getSanPham();
    @GET("api/THELOAIs")
    Call<List<TheLoai>>getTheLoai();

    //admin
    @POST("api/SANPHAMs")
    Call<SanPham>addSanPham(@Body SanPham sanpham);
    @DELETE("api/SANPHAMs/{maSP}")
    Call<String> deleteSanpham(@Path("maSP") String id);
    @PUT("api/SANPHAMs/{maSP}")
    Call<String> updateSanpham(@Path("maSP") String id,@Body SanPham sanPham);
}