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
import android.widget.Toast;
import java.util.List;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // Start MainActivity
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);

        // Start SecondActivity
//        Intent secondIntent = new Intent(this, SecondActivity.class);
//        startActivity(secondIntent);

        // Finish the launcher activity
        finish();
    }
}
