package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import group6.sa3.model.Dog;
import group6.sa3.retrofit.DogApi;
import group6.sa3.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dogs);

        final Button addDogButton = (Button) findViewById(R.id.btnadddog);

        addDogButton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), NewDog.class));
        });
    }

    @Override
    protected void onResume() {
        final RetrofitService retrofitService = new RetrofitService();
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        final Gson gson = new Gson();

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
                        Toast.makeText(AddDogs.this, "Error Retrieving Dog List", Toast.LENGTH_SHORT).show();
                    }
                });

        List<Dog> dogList = gson.fromJson(sharedPref.getString("DogList", (gson.toJson(new ArrayList<Dog>()))), new TypeToken<List<Dog>>() {}.getType());

        String[] dogNames = dogList.stream().map(dog -> dog.getName()).toArray(size -> new String[size]);
        String[] dogBreeds = dogList.stream().map(dog -> dog.getBreed()).toArray(size -> new String[size]);

        ListView listView = (ListView) findViewById(R.id.admin_dog_list);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), dogNames, dogBreeds);
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditDog.class);
                Gson gson = new Gson();
                intent.putExtra("Dog", gson.toJson(dogList.get(position)));
                startActivity(intent);
            }
        });
        super.onResume();
    }


}