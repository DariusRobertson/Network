package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

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
    private RecyclerView mRecylerView;
    private exampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Example_list> itemTemplates;

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
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        createExampleList();
        buildRecylerView();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               if (isClickedItem(engineeringCheckBox) || isClickedItem(mathCheckbox) || isClickedItem(spaceCheckbox) ||
                isClickedItem(ScienceCheckbox)) {
                    stemCount += 10;
                    humanitiesCount--;
                    artsyCount--;
                   startActivity(new Intent(Category.this,MainActivity.class));
                }
                  else  if (isClickedItem(artCheckBox) || isClickedItem(signingCheckbox) || isClickedItem(englishCheckbox) ||
                    isClickedItem(dancingCheckbox)){
                        humanitiesCount += 5;
                        artsyCount += 5;
                        stemCount --;
                    startActivity(new Intent(Category.this,MainActivity.class));
                    }

                else if (!isClickedItem(artCheckBox) && !isClickedItem(signingCheckbox) && !isClickedItem(englishCheckbox) &&
                        !isClickedItem(dancingCheckbox) && !isClickedItem(engineeringCheckBox) && !isClickedItem(mathCheckbox) && isClickedItem(spaceCheckbox) &&
                        !isClickedItem(ScienceCheckbox)){
                    Toast.makeText(Category.this, "Please select a category", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void changeItem(int position, String text){
        itemTemplates.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }


    private void buildRecylerView() {
        mRecylerView = findViewById(R.id.recylerView);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new exampleAdapter(itemTemplates);

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new exampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Selected");
            }
        });
    }

    private void createExampleList() {
        itemTemplates = new ArrayList<>();
        itemTemplates.add(new Example_list("Software Engineer"));
        itemTemplates.add(new Example_list("Accountant"));
        itemTemplates.add(new Example_list("Teacher or professor"));
        itemTemplates.add(new Example_list("Lawyer"));
        itemTemplates.add(new Example_list("Mechanical engineer"));
        itemTemplates.add(new Example_list("Electrical Engineer"));
        itemTemplates.add(new Example_list("Nurse"));
        itemTemplates.add(new Example_list("Surgeon"));
        itemTemplates.add(new Example_list("Doctor"));
        itemTemplates.add(new Example_list("Dentist"));
    }

    public String occupation(String occupation){

        return occupation;
    }




    public boolean isClickedItem(View v){
        CheckBox checkBox = (CheckBox)v;
        return checkBox.isChecked();
    }
}