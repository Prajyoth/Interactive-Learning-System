package com.example.prajyoth.interactivelearningsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StorySelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_selection);
    }

    public void playStory(View view) {
        Intent intent = new Intent(this, PlayStory.class);
        startActivity(intent);
    }
}
