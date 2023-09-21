package vn.edu.usth.gmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.view.GravityCompat;

import android.view.MenuItem;
import java.util.List;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener,KeyboardVisibilityUtils.OnKeyboardVisibilityListener{

    RecyclerView recyclerView;
    List<User> userList;
    CustomAdapter customAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Button compose_button;

    CoordinatorLayout coordinatorLayout;
    BottomAppBar bottomAppBar;

    BottomNavigationView bottomNavigationView;
    ExtendedFloatingActionButton extendedFloatingActionButton;
    KeyboardVisibilityUtils keyboardVisibilityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayItems();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        compose_button = findViewById(R.id.Compose);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        extendedFloatingActionButton = findViewById(R.id.Compose);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View rootView = findViewById(android.R.id.content);
        keyboardVisibilityUtils = new KeyboardVisibilityUtils(rootView, this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        compose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ComposeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.logout) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                else if (item.getItemId() == R.id.star){
                    Intent intent = new Intent(getApplicationContext(),ComposeActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.chat) {
                    startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.settings) {
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    finish();
                    return true;
                }

                return false;
            }
        });

    }

    // Inside your MainActivity.java

//


    @Override
    public void onKeyboardVisibilityChanged(boolean isVisible) {
        if (isVisible) {
            // Keyboard is open, hide the BottomAppBar and FAB
            bottomAppBar.setVisibility(View.GONE);
            extendedFloatingActionButton.setVisibility(View.GONE);
        } else {
            // Keyboard is closed, show the BottomAppBar and FAB
            bottomAppBar.setVisibility(View.VISIBLE);
            extendedFloatingActionButton.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();
    }}

    private void displayItems() {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        userList = new ArrayList<>();

        userList.add(new User("Phan Anh", "Helloooo", "XIn Chao", R.drawable.a));
        userList.add(new User("Trong Duc", "fwefwefwef", "hrethrth", R.drawable.c));
        userList.add(new User("Bui Duc", "herthert", "hretherh", R.drawable.e));
        userList.add(new User("Hoang Nam", "Helloooo", "XIn Chao", R.drawable.f));
        userList.add(new User("Phan Anh", "Helloooo", "XIn Chao", R.drawable.a));
        userList.add(new User("Minh Duc", "Helloooo", "XIn Chao", R.drawable.b));
        userList.add(new User("Trong Duc", "Helloooo", "XIn Chao", R.drawable.c));
        userList.add(new User("Bui Duc", "Helloooo", "XIn Chao", R.drawable.e));
        userList.add(new User("Hoang Nam", "Helloooo", "XIn Chao", R.drawable.f));
        userList.add(new User("Phan Anh", "Helloooo", "XIn Chao", R.drawable.a));
        userList.add(new User("Minh Duc", "Helloooo", "XIn Chao", R.drawable.b));
        userList.add(new User("Trong Duc", "Helloooo", "XIn Chao", R.drawable.c));
        userList.add(new User("Bui Duc", "Helloooo", "XIn Chao", R.drawable.e));
        userList.add(new User("Hoang Nam", "Helloooo", "XIn Chao", R.drawable.f));
        userList.add(new User("Phan Anh", "Helloooo", "XIn Chao", R.drawable.a));
        userList.add(new User("Minh Duc", "Helloooo", "XIn Chao", R.drawable.b));
        userList.add(new User("Trong Duc", "Helloooo", "XIn Chao", R.drawable.c));
        userList.add(new User("Bui Duc", "Helloooo", "XIn Chao", R.drawable.e));
        userList.add(new User("Hoang Nam", "Helloooo", "XIn Chao", R.drawable.f));



        customAdapter = new CustomAdapter(this, userList, this);
        recyclerView.setAdapter(customAdapter);

    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(MainActivity.this, Detail_1.class);
        intent.putExtra("Name", userList.get(position).getName());
        intent.putExtra("Head Mail", userList.get(position).getHead_mail());
        intent.putExtra("Content", userList.get(position).getContent());
        intent.putExtra("Image", userList.get(position).getImage());
        startActivity(intent);
    }




    @Override
        public void onStart() {
            super.onStart();
            Log.i("MainActivity", "OnStart");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.i("MainActivity", "OnPause");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.i("MainActivity", "OnResume");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.i("MainActivity", "OnStop");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i("MainActivity", "OnDestroy");
        }



}


