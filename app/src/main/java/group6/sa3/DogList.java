package group6.sa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DogList extends AppCompatActivity {
    String dogNameList[] = {"Dog 1", "Dog 2", "Dog 3"};
    String dogBreedList[] = {"German Shepherd", "Bulldog", "Labrador Retriever"};

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        listView = findViewById(R.id.dog_list);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), dogNameList, dogBreedList);
        listView.setAdapter(customBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DogList.this, ViewDog.class);
                intent.putExtra("DogName", dogNameList[position]);
                intent.putExtra("DogBreed", dogBreedList[position]);
                startActivity(intent);
            }
        });
    }
}