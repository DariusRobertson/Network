package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
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
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    private TextView newUser;
    private Button loginButton;
    private EditText userEmail;
    private EditText userPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setting newUser to the sign Up button
        newUser = findViewById(R.id.signUp);
        loginButton = findViewById(R.id.login);
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);



        //sends user to signUp field if clicked and user !=null
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });

        //Login function. Set up with firebase
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringEmail = userEmail.getText().toString();
                String stringPassword = userPassword.getText().toString();

                //handling some possible user errors
                if (stringEmail.isEmpty()) {
                    Toast.makeText(Login.this, "Email is empty", Toast.LENGTH_SHORT).show();

                } else if (stringPassword.isEmpty()) {
                    Toast.makeText(Login.this, "Password is empty", Toast.LENGTH_SHORT).show();

                } else if (!isValidEmail(stringEmail)) {
                    Toast.makeText(Login.this, "email is not valid", Toast.LENGTH_SHORT).show();

                } else if (!isValidPassword(stringPassword)) {
                    Toast.makeText(Login.this, "Password must be > 7", Toast.LENGTH_LONG).show();

                } else {
                    mAuth.signInWithEmailAndPassword(stringEmail, stringPassword)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Good", "SignIn Successful");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(new Intent(Login.this,MainActivity.class));
                                    } else {
                                        Log.w("Fail", "SignIn:Failed", task.getException());
                                        Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
            }
        });
    }
    //signs in user if they didn't logout
    public void onStart() {
        FirebaseAuth fAuth;
        fAuth = FirebaseAuth.getInstance();
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(fAuth.getCurrentUser() != null)
            startActivity(new Intent(Login.this,MainActivity.class));
    }



    private boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private boolean isValidPassword(String password){
        return password.length() > 7;
    }
    }
