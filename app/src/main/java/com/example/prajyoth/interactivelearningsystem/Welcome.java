package com.example.prajyoth.interactivelearningsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }

    //called when user clicks story
    public void selectStory(View view) {
        Intent intent = new Intent(this, StorySelection.class);
        startActivity(intent);
    }
}
