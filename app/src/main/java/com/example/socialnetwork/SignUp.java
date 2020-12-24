package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText customerName;
    private EditText userEmail;
    private EditText userPassword;
    private FirebaseAuth mAuth;
    private Button nextBttn;
    private TextView existingUser;
    private Button testButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        customerName = findViewById(R.id.Name);
        userEmail = findViewById(R.id.Useremail);
        nextBttn = findViewById(R.id.NextLogin);
        userPassword = findViewById(R.id.NewUserPassword);
        mAuth = FirebaseAuth.getInstance();
        existingUser = findViewById(R.id.sendToLogin);
        testButton = findViewById(R.id.TestingButton);

        //Button used to get to categories screen without having to create new users over and over
        //for testing only
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Category.class));
            }
        });


        //sending a user back to login if they already have an account
        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

        //Creating a new user with firebase
      nextBttn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String password = userPassword.getText().toString();
              final String email = userEmail.getText().toString();
              final String name = customerName.getText().toString();
              if(password.isEmpty() && name.isEmpty() && email.isEmpty()){
                  Toast.makeText(SignUp.this,"Please fill in text fields before hitting next.",Toast.LENGTH_LONG).show();
              } else if (email.isEmpty()) {
                  Toast.makeText(SignUp.this, "Email is empty", Toast.LENGTH_SHORT).show();
              } else if (password.isEmpty()) {
                  Toast.makeText(SignUp.this, "Password is empty", Toast.LENGTH_SHORT).show();
              } else if (name.isEmpty()) {
                  Toast.makeText(SignUp.this, "password is empty", Toast.LENGTH_SHORT).show();
              } else if (!isValidEmail(email)) {
                  Toast.makeText(SignUp.this, "email is not valid", Toast.LENGTH_SHORT).show();
              } else if (!isValidPassword(password)) {
                  Toast.makeText(SignUp.this, "Password must be > 7", Toast.LENGTH_LONG).show();
              } else {
                  mAuth.createUserWithEmailAndPassword(email, password)
                          .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()) {
                                      Log.d("Passed", "CreatedUserWithEmail:Success");
                                      FirebaseUser user = mAuth.getCurrentUser();
                                      UserProfileChangeRequest addName = new UserProfileChangeRequest.Builder()
                                              .setDisplayName(name).build();
                                      assert user != null;
                                      user.updateProfile(addName);
                                      startActivity(new Intent(SignUp.this, Category.class));

                                  } else {
                                      Log.w("Failed", "AccountCreation:Failed", task.getException());
                                      Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });


              }
          }
      });
    }
    private boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private boolean isValidPassword(String password){
        return password.length() > 7;
    }

}