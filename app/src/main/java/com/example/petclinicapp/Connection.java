package com.example.petclinicapp;

public class Connection {

    private Connection(){};

    public static final String API_URL = "dir";

    public static ReportService getServiceRemote(){
        return Client.getClient(API_URL).create(ReportService.class);
    }
}
