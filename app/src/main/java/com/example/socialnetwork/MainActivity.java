package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Button homeButton;
    private Button messagesButton;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



        /***ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
               R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
         toggle.syncState();
         ***/

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

    }
    //bottom nav items
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.main:
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            break;
                        case R.id.messages:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_bottom_container,
                                    new MessagesFragment()).commit();
                            break;
                    }

                    return true;
                }
            };



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }



    //top right menu select options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new profileFragment()).commit();
                break;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut(); //logs out the user
                Toast.makeText(MainActivity.this,"Logout Successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class)); //sends to login activity
                finish();
                break;

            case R.id.explore:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ExploreFragment()).commit();
                break;

            case R.id.promotion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_bottom_container,
                        new PromoteFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    //left navigation screen items selections
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new profileFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut(); //logs out the user
                Toast.makeText(this,"Logout Successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class)); //sends to login activity
                finish();
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.explore:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_bottom_container,
                        new ExploreFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.promotion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_bottom_container,
                        new PromoteFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}