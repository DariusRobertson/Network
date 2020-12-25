package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Category extends AppCompatActivity {

    private Button engineeringCheckBox;
    private Button artCheckBox;
    private Button spaceCheckbox;
    private Button historyCheckBox;
    private Button finish;
    private Button ScienceCheckbox;
    private Button englishCheckbox;
    private Button mathCheckbox;
    private Button signingCheckbox;
    private Button dancingCheckbox;

    private int stemCount = 0;
    private int artsyCount = 0;
    private int humanitiesCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        finish = findViewById(R.id.Finish);
        engineeringCheckBox = findViewById(R.id.engineeringBox);
        artCheckBox = findViewById(R.id.artBox);
        spaceCheckbox = findViewById(R.id.SpaceBox);
        historyCheckBox = findViewById(R.id.historyBox);
        ScienceCheckbox = findViewById(R.id.ScienceBox);
        englishCheckbox = findViewById(R.id.englishBox);
        mathCheckbox = findViewById(R.id.MathBox);
        signingCheckbox = findViewById(R.id.signingBox);
        dancingCheckbox = findViewById(R.id.dancingBox);



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClickedItem(engineeringCheckBox))
                    stemCount++;
                if (isClickedItem(artCheckBox))
                    artsyCount++;
                if (isClickedItem(spaceCheckbox))
                    stemCount++;
                if (isClickedItem(historyCheckBox))
                    humanitiesCount++;
                if (isClickedItem(ScienceCheckbox))
                    stemCount++;
                if (isClickedItem(englishCheckbox)) {
                    humanitiesCount++;
                    artsyCount++;
                }
                if (isClickedItem(mathCheckbox))
                    stemCount++;
                if (isClickedItem(signingCheckbox))
                    artsyCount ++;
                if (isClickedItem(dancingCheckbox))
                    artsyCount ++;

                if (isClickedItem(engineeringCheckBox) && isClickedItem(mathCheckbox) && isClickedItem(spaceCheckbox) &&
                isClickedItem(ScienceCheckbox)) {
                    stemCount += 10;
                    humanitiesCount--;
                    artsyCount--;
                }
                    if (isClickedItem(artCheckBox) && isClickedItem(signingCheckbox) && isClickedItem(englishCheckbox) &&
                    isClickedItem(dancingCheckbox)){
                        humanitiesCount += 5;
                        artsyCount += 5;
                        stemCount --;
                    }

                if (!isClickedItem(artCheckBox) && !isClickedItem(signingCheckbox) && !isClickedItem(englishCheckbox) &&
                        !isClickedItem(dancingCheckbox) && !isClickedItem(engineeringCheckBox) && !isClickedItem(mathCheckbox) && isClickedItem(spaceCheckbox) &&
                        !isClickedItem(ScienceCheckbox)){
                    Toast.makeText(Category.this, "Please select a category", Toast.LENGTH_LONG).show();
                }else
                    startActivity(new Intent(Category.this,MainActivity.class));

            }
        });


    }

    public String occupation(String occupation){

        return occupation;
    }




    public boolean isClickedItem(View v){
        CheckBox checkBox = (CheckBox)v;
        return checkBox.isChecked();
    }
}