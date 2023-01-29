package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog);

        EditText editName = (EditText) findViewById(R.id.editText_Dog_Name);
        EditText editBreed = (EditText) findViewById(R.id.editText_Dog_Breed);

        editName.setText(getIntent().getExtras().getString("DogName"));
        editBreed.setText(getIntent().getExtras().getString("DogBreed"));
    }
}