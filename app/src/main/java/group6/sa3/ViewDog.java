package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dog);

        TextView dogNameDisplay = (TextView) findViewById(R.id.dogNameDisplay);
        TextView dogBreedDisplay = (TextView) findViewById(R.id.dogBreedDisplay);

        dogNameDisplay.setText("Name: " + getIntent().getExtras().getString("DogName"));
        dogBreedDisplay.setText("Breed: " + getIntent().getExtras().getString("DogBreed"));
    }
}