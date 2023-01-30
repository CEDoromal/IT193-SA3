package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import group6.sa3.model.Dog;

public class ViewDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dog);

        TextView dogNameDisplay = (TextView) findViewById(R.id.dogNameDisplay);
        TextView dogBreedDisplay = (TextView) findViewById(R.id.dogBreedDisplay);
        TextView dogAgeDisplay = (TextView) findViewById(R.id.dogAgeDisplay);
        TextView dogColorDisplay = (TextView) findViewById(R.id.dogColorDisplay);
        TextView dogVaccDisplay = (TextView) findViewById(R.id.dogVaccDisplay);
        TextView dogTraitDisplay = (TextView) findViewById(R.id.dogTraitDisplay);

        Gson gson = new Gson();
        Dog dog = gson.fromJson(getIntent().getExtras().getString("Dog"), Dog.class);

        dogNameDisplay.setText(dog.getName());
        dogBreedDisplay.setText(dog.getBreed());
        dogAgeDisplay.setText(String.valueOf(dog.getAge()));
        dogColorDisplay.setText(dog.getColor());
        dogVaccDisplay.setText(dog.getVaccinated());
        dogTraitDisplay.setText(dog.getTraits());
    }
}