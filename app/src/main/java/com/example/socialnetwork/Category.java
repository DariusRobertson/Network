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
    private TextView occupation;

    private int stemCount = 0;
    private int artsyCount = 0;
    private int humanitiesCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        engineeringCheckBox = findViewById(R.id.checkBox3);
        artCheckBox = findViewById(R.id.checkBox4);
        spaceCheckbox = findViewById(R.id.checkBox5);
        historyCheckBox = findViewById(R.id.checkBox6);
        finish = findViewById(R.id.Finish);
        occupation = findViewById(R.id.textView9);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickedItem(engineeringCheckBox) && isClickedItem(spaceCheckbox)
                && !isClickedItem(artCheckBox) && !isClickedItem(historyCheckBox)){
                    stemCount += 10;
                    artsyCount--;
                    humanitiesCount--;
                }else if (!isClickedItem(engineeringCheckBox) && isClickedItem(spaceCheckbox)
                        && !isClickedItem(artCheckBox) && !isClickedItem(historyCheckBox)){
                    stemCount += 5;
                    humanitiesCount++;
                    artsyCount++;
                }else if (!isClickedItem(engineeringCheckBox) && !isClickedItem(spaceCheckbox)
                        && isClickedItem(artCheckBox) && isClickedItem(historyCheckBox)){
                    humanitiesCount += 5;
                    artsyCount += 5;
                    stemCount--;
                }
                Toast.makeText(Category.this, "Profile Created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Category.this,MainActivity.class));

            }
        });
    }




    public boolean isClickedItem(View v){
        CheckBox checkBox = (CheckBox)v;
        return checkBox.isChecked();
    }
}