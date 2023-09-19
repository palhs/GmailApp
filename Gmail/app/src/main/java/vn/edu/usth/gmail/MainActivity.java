package vn.edu.usth.gmail;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import androidx.core.view.GravityCompat;
import android.widget.Toast;
import android.view.MenuItem;
import java.util.List;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    List<User> userList;
    CustomAdapter customAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayItems();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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


    }

    // Inside your MainActivity.java


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


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


