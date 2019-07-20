package com.example.petclinicapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportService {

    @GET("citas/")
    Call<List<Cita>> getReports();

    @POST("citas/")
    Call<Cita> addReport(@Body Cita cita);

    @GET("citas/{id}")
    Call<Cita> getByIdReport(@Path("id") int id);

    @PUT("citas/{id}")
    Call<Cita> updateReport(@Path("id") int id, @Body Cita cita);

    @DELETE("citas/{id}")
    Call<Cita> deleteReport(@Path("id") int id);

}