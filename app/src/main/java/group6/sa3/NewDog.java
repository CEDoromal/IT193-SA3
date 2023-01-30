package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import group6.sa3.model.Dog;
import group6.sa3.retrofit.DogApi;
import group6.sa3.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dog);


        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        Button btnBack = (Button) findViewById(R.id.btnBack2);
        EditText editName = (EditText) findViewById(R.id.editText_Dog_Name);
        EditText editBreed = (EditText) findViewById(R.id.editText_Dog_Breed);
        EditText editAge = (EditText) findViewById(R.id.editText_Dog_Age);
        EditText editColor = (EditText) findViewById(R.id.editText_Dog_Color);
        EditText editVacc = (EditText) findViewById(R.id.editText_Dog_Vaccinated);
        EditText editTrait = (EditText) findViewById(R.id.editText_Dog_Traits);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);
        Gson gson = new Gson();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        btnSubmit.setOnClickListener(view -> {
            Dog dog = new Dog();
            dog.setName(editName.getText().toString());
            dog.setBreed(editBreed.getText().toString());
            dog.setAge(Integer.parseInt(editAge.getText().toString()));
            dog.setColor(editColor.getText().toString());
            dog.setVaccinated(editVacc.getText().toString());
            dog.setTraits(editTrait.getText().toString());
            dogApi.addDog(dog)
                    .enqueue(new Callback<Dog>() {
                        @Override
                        public void onResponse(Call<Dog> call, Response<Dog> response) {
                            dogApi.getAllDog()
                                    .enqueue(new Callback<List<Dog>>() {
                                        @Override
                                        public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                                            SharedPreferences.Editor editor = sharedPref.edit();
                                            editor.putString("DogList", gson.toJson(response.body()));
                                            editor.commit();
                                            Toast.makeText(NewDog.this, "Dog Record Successfully Added", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }

                                        @Override
                                        public void onFailure(Call<List<Dog>> call, Throwable t) {
                                            Toast.makeText(NewDog.this, "Dog List Refresh Failed", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });

                        }

                        @Override
                        public void onFailure(Call<Dog> call, Throwable t) {
                            Toast.makeText(NewDog.this, "Dog Record Addition Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        btnBack.setOnClickListener(view -> {
            finish();
        });
    }
}