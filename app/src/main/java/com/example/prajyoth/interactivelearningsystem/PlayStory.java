package com.example.prajyoth.interactivelearningsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_story);
    }

    public void girlBasket(View view) {
        Intent intent = new Intent(this, GirlBasket.class);
        startActivity(intent);
    }
}
