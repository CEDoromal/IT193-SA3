package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import group6.sa3.model.Dog;
import group6.sa3.retrofit.DogApi;
import group6.sa3.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog);

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        Button btnBack = (Button) findViewById(R.id.btnBack3);
        EditText editName = (EditText) findViewById(R.id.editText_Dog_Name);
        EditText editBreed = (EditText) findViewById(R.id.editText_Dog_Breed);
        EditText editAge = (EditText) findViewById(R.id.editText_Dog_Age);
        EditText editColor = (EditText) findViewById(R.id.editText_Dog_Color);
        EditText editVacc = (EditText) findViewById(R.id.editText_Dog_Vaccinated);
        EditText editTrait = (EditText) findViewById(R.id.editText_Dog_Traits);

        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

        Gson gson = new Gson();
        Dog dog = gson.fromJson(getIntent().getExtras().getString("Dog"), Dog.class);

        editName.setText(dog.getName());
        editBreed.setText(dog.getBreed());
        editAge.setText(String.valueOf(dog.getAge()));
        editColor.setText(dog.getColor());
        editVacc.setText(dog.getVaccinated());
        editTrait.setText(dog.getTraits());

        btnDelete.setOnClickListener(view -> {
            dogApi.deleteDog(dog.getId())
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(EditDog.this, "Dog Record Successfully Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(EditDog.this, "Dog Record Deletion Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            finish();
        });

        btnSubmit.setOnClickListener(view -> {
            dog.setName(editName.getText().toString());
            dog.setBreed(editBreed.getText().toString());
            dog.setAge(Integer.parseInt(editAge.getText().toString()));
            dog.setColor(editColor.getText().toString());
            dog.setVaccinated(editVacc.getText().toString());
            dog.setTraits(editTrait.getText().toString());
            dogApi.updateDog(dog)
                    .enqueue(new Callback<Dog>() {
                        @Override
                        public void onResponse(Call<Dog> call, Response<Dog> response) {
                            Toast.makeText(EditDog.this, "Dog Record Successfully Updated", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Dog> call, Throwable t) {
                            Toast.makeText(EditDog.this, "Dog Record Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        btnBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void logout() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("activeAccount");
        editor.commit();
    }

    @Override
    public void onDestroy() {
        logout();
        super.onDestroy();
    }
}