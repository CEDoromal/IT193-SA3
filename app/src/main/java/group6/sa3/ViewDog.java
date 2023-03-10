package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import group6.sa3.model.Dog;

public class ViewDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dog);

        TextView dogName = (TextView) findViewById(R.id.dogName);
        TextView dogNameDisplay = (TextView) findViewById(R.id.dogNameDisplay);
        TextView dogBreedDisplay = (TextView) findViewById(R.id.dogBreedDisplay);
        TextView dogAgeDisplay = (TextView) findViewById(R.id.dogAgeDisplay);
        TextView dogColorDisplay = (TextView) findViewById(R.id.dogColorDisplay);
        TextView dogVaccDisplay = (TextView) findViewById(R.id.dogVaccDisplay);
        TextView dogTraitDisplay = (TextView) findViewById(R.id.dogTraitDisplay);
        final Button buttonApply = (Button) findViewById(R.id.btnApply);
        final Button buttonBack1 = (Button) findViewById(R.id.btnBack);

        Gson gson = new Gson();
        Dog dog = gson.fromJson(getIntent().getExtras().getString("Dog"), Dog.class);

        dogName.setText(dog.getName());
        dogNameDisplay.setText("Name: " + dog.getName());
        dogBreedDisplay.setText("Breed: " + dog.getBreed());
        dogAgeDisplay.setText("Age: " + String.valueOf(dog.getAge()));
        dogColorDisplay.setText("Color: " + dog.getColor());
        dogVaccDisplay.setText("Vaccinated: " + dog.getVaccinated());
        dogTraitDisplay.setText("Trait: " + dog.getTraits());

        buttonApply.setOnClickListener(view -> {
            Toast.makeText(this, "You have successfully applied for adoption", Toast.LENGTH_LONG).show();
        });

        buttonBack1.setOnClickListener(view -> {
            finish();
        });

    }
}