package app.petclinic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView email;
    TextView password;
    TextView warnEmail;
    TextView warnPassword;
    TextView warnLogin;
    String nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void login(View view) {
        if(validarCamposLogin()){
            Data data = new Data();
            data.setEmail(email.getText().toString().trim());
            data.setPassword(password.getText().toString().trim());
            conectLogin(data);
            warnLogin.setVisibility(View.GONE);
        }

    }

    private void conectLogin(Data d) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(email.getWindowToken(), 0);
        Retrofit retrofit = Connection.getClient();
        DataService dataService = retrofit.create(DataService.class);
        Call<Data> call = dataService.login(d);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.body().getResponse().equals("1")){
                    screenPosLogin(response.body().getName(), response.body().getUserId());
                }else{
                    warnLogin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                warnLogin.setText("Intente más tarde");
                warnLogin.setVisibility(View.VISIBLE);
            }
        });
    }


    private boolean validarCamposLogin(){
        warnEmail.setVisibility(View.GONE);
        warnPassword.setVisibility(View.GONE);
        if(email.getText().toString().trim().equals("")){
            warnEmail.setVisibility(View.VISIBLE);
            return false;
        }else if (password.getText().toString().trim().equals("")){
            warnPassword.setVisibility(View.VISIBLE);
            return false;
        }else{
            warnEmail.setVisibility(View.GONE);
            warnPassword.setVisibility(View.GONE);
            return true;
        }
    }

    private void init(){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        warnEmail = findViewById(R.id.warnEmail);
        warnPassword = findViewById(R.id.warnPassword);
        warnEmail.setVisibility(View.GONE);
        warnPassword.setVisibility(View.GONE);
        warnLogin = findViewById(R.id.errorLogin);
        warnLogin.setVisibility(View.GONE);
    }

    public void screenPosLogin(String name, String id){
        nameUser= name;
        Retrofit retrofit = Connection.getClient();
        DataService dataService = retrofit.create(DataService.class);
        Call<Data> call = dataService.getOwnerId(id);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Intent screen = new Intent(MainActivity.this, PanelActivity.class);
                screen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                screen.putExtra("name", nameUser);
                screen.putExtra("id",response.body().getOwner());
                startActivity(screen);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


}
