package group6.sa3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button aboutButton = (Button) findViewById(R.id.abtBttn);
        final Button adoptNow = (Button) findViewById(R.id.adoptbttn);
        final Button outButton = (Button) findViewById(R.id.logoutButton);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        aboutButton.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AboutUs.class));
        });

        adoptNow.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), DogList.class));
        });

        outButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("activeAccount");
            finish();
        });

    }

}
