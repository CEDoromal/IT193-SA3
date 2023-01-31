package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import group6.sa3.model.Dog;
import group6.sa3.retrofit.DogApi;
import group6.sa3.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogList extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);
    }

    @Override
    protected void onResume() {
        final RetrofitService retrofitService = new RetrofitService();
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        final Gson gson = new Gson();

        /*
        dogApi.getAllDog()
                .enqueue(new Callback<List<Dog>>() {
                    @Override
                    public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("DogList", gson.toJson(response.body()));
                        editor.commit();
                    }

                    @Override
                    public void onFailure(Call<List<Dog>> call, Throwable t) {
                        Toast.makeText(DogList.this, "Error Retrieving Dog List", Toast.LENGTH_SHORT).show();
                    }
                });

        List<Dog> dogList = gson.fromJson(sharedPref.getString("DogList", (gson.toJson(new ArrayList<Dog>()))), new TypeToken<List<Dog>>() {}.getType());
         */

        //

        List<Dog> responseBody;

        try {
            responseBody = dogApi.getAllDog().execute().body();
        } catch (IOException e) {
            responseBody = new ArrayList<Dog>();
            Toast.makeText(this, "Failed to get Dog List", Toast.LENGTH_SHORT).show();
        }

        List<Dog> dogList = responseBody; // needed or else can't be accessed from inner class

        //

        String[] dogNames = dogList.stream().map(dog -> dog.getName()).toArray(size -> new String[size]);
        String[] dogBreeds = dogList.stream().map(dog -> dog.getBreed()).toArray(size -> new String[size]);

        ListView listView = (ListView) findViewById(R.id.dog_list);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), dogNames, dogBreeds);
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewDog.class);
                Gson gson = new Gson();
                intent.putExtra("Dog", gson.toJson(dogList.get(position)));
                startActivity(intent);
            }
        });
        super.onResume();
    }

}