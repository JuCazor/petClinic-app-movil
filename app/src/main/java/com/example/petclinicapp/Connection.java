package app.petclinic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
    private static Retrofit retrofit = null;
    public static final String API_URL= "http://192.168.43.144:8080/api/";


    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
