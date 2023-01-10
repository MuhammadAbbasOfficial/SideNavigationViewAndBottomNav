package com.example.BottomNavigationAndSideNavigation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.BottomNavigationAndSideNavigation.R;
import com.example.BottomNavigationAndSideNavigation.fragments.camera_frag;
import com.example.BottomNavigationAndSideNavigation.fragments.conversation_frag;
import com.example.BottomNavigationAndSideNavigation.fragments.dictionary_frag;
import com.example.BottomNavigationAndSideNavigation.fragments.translate_frag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    FrameLayout frameLayout;
    private long back_pressed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView =(NavigationView) findViewById(R.id.side_nave);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //bottomNavigation listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId())
                {
                    case R.id.translate:
                        transaction.replace(R.id.frameLayout, new translate_frag());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    break;
                    case R.id.conversation:
                        transaction.replace(R.id.frameLayout, new conversation_frag());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.camera:
                        transaction.replace(R.id.frameLayout, new camera_frag());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.dictionary:
                        transaction.replace(R.id.frameLayout, new dictionary_frag());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }

                return true;
            }
        });
        //select current nav
        bottomNavigationView.setSelectedItemId(R.id.translate);

        //setting up side navigation view
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId())
                {
                    case R.id.translate:
                        transaction.replace(R.id.frameLayout, new translate_frag());
                        transaction.commit();
                        bottomNavigationView.setSelectedItemId(R.id.translate);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.conversation:
                        transaction.replace(R.id.frameLayout, new conversation_frag());
                        transaction.commit();
                        bottomNavigationView.setSelectedItemId(R.id.conversation);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.camera:
                        transaction.replace(R.id.frameLayout, new camera_frag());
                        transaction.commit();
                        bottomNavigationView.setSelectedItemId(R.id.camera);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.dictionary:
                        transaction.replace(R.id.frameLayout, new dictionary_frag());
                        transaction.commit();
                        bottomNavigationView.setSelectedItemId(R.id.dictionary);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId())
        {
            case R.id.translate:
                transaction.replace(R.id.frameLayout, new translate_frag());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.conversation:
                transaction.replace(R.id.frameLayout, new conversation_frag());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.camera:
                transaction.replace(R.id.frameLayout, new camera_frag());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.dictionary:
                transaction.replace(R.id.frameLayout, new dictionary_frag());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                finish();
                moveTaskToBack(true);
            } else {
                Snackbar.make(frameLayout, "Press Again to Exit", Snackbar.LENGTH_LONG).show();
                back_pressed = System.currentTimeMillis();
            }
            //super.onBackPressed();
        }
    }


}