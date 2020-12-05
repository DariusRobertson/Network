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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {

    private EditText customerName;
    private EditText userEmail;
    private EditText userPassword;
    private FirebaseAuth mAuth;
    private Button nextBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        customerName = findViewById(R.id.Name);
        userEmail = findViewById(R.id.Useremail);
        nextBttn = findViewById(R.id.NextLogin);
        userPassword = findViewById(R.id.NewUserPassword);
        mAuth = FirebaseAuth.getInstance();

        //Creating a new user with firebase
      nextBttn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String password = userPassword.getText().toString();
              String email = userEmail.getText().toString();
              final String name = customerName.getText().toString();
              if (email.isEmpty()) {
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
                                      user.updateProfile(addName);
                                      startActivity(new Intent(SignUp.this, SecondSignUp.class));

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