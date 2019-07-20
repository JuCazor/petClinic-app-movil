package com.example.petclinicapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Time;

public class Cita {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("owner")
    @Expose
    private String owner;

    @SerializedName("mascota")
    @Expose
    private String mascota;

    @SerializedName("fecha")
    @Expose
    private Date fecha;

    @SerializedName("hora")
    @Expose
    private Time hora;

    public Cita(){
    }
    public Cita(int id, String owner, Date fecha, Time hora){
        this.id = id;
        this.owner = owner;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return owner;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.owner = owner;
    }
}
