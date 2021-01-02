package com.example.socialnetwork;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainPostAction extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPriority;
    private Button postToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post_action);
        editTextDescription = findViewById(R.id.userDescription);
        editTextTitle = findViewById(R.id.userTitle);
        numberPriority = findViewById(R.id.number_picker_priority);
        postToMain = findViewById(R.id.postToMain);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);



        numberPriority.setMinValue(1);
        numberPriority.setMaxValue(10);

        postToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNote();
            }
        });
    }

    private void postNote(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"Title or Description is empty", Toast.LENGTH_SHORT).show();
        }
        CollectionReference postRef = FirebaseFirestore.getInstance()
                .collection("Post");
        postRef.add(new Post(title,priority,description));
        Toast.makeText(this,"Post Created",Toast.LENGTH_SHORT).show();
        finish();
    }
}