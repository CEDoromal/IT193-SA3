package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import group6.sa3.model.Account;
import group6.sa3.retrofit.AccountApi;
import group6.sa3.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText personName = (EditText) findViewById(R.id.editTextTextPersonName);
        final Button loginBtn = (Button) findViewById(R.id.btnlogin);
        final Button registerBtn = (Button) findViewById(R.id.btnregister);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final RetrofitService retrofitService = new RetrofitService();
        final AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);


        loginBtn.setOnClickListener(view -> {
            accountApi.getAccount(personName.getText().toString())
                    .enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = sharedPref.edit();
                                Gson gson = new Gson();
                                editor.putString("activeAccount", gson.toJson(response.body()));
                                editor.commit();
                                if(response.body().isAdmin()) startActivity(new Intent(getApplicationContext(), AddDogs.class));
                                else startActivity(new Intent(getApplicationContext(), Home.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    });
        });

        registerBtn.setOnClickListener(view -> {
            accountApi.addAccount(new Account(personName.getText().toString(), false))
                    .enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {

                            if(response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                SharedPreferences.Editor editor = sharedPref.edit();
                                Gson gson = new Gson();
                                editor.putString("activeAccount", gson.toJson(response.body()));
                                editor.commit();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    });
        });


    }
}