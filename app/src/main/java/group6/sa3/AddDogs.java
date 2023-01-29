package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class AddDogs extends AppCompatActivity {
    String dogNameList[] = {"Dog 1", "Dog 2", "Dog 3"};
    String dogBreedList[] = {"German Shepherd", "Bulldog", "Labrador Retriever"};

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dogs);

        listView = findViewById(R.id.admin_dog_list);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), dogNameList, dogBreedList);
        listView.setAdapter(customBaseAdapter);

        Button addDog = findViewById(R.id.btnadddog);
        addDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDogs.this, NewDog.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AddDogs.this, EditDog.class);
                intent.putExtra("DogName", dogNameList[position]);
                intent.putExtra("DogBreed", dogBreedList[position]);
                startActivity(intent);
            }
        });
    }
}