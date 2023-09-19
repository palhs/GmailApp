package vn.edu.usth.gmail;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    List<User> userList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayItems();
    }

    private void displayItems() {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        userList = new ArrayList<>();

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
        userList.add(new User("Phan Anh", "Helloooo", "XIn Chao", R.drawable.a));
        userList.add(new User("Minh Duc", "Helloooo", "XIn Chao", R.drawable.b));
        userList.add(new User("Trong Duc", "Helloooo", "XIn Chao", R.drawable.c));
        userList.add(new User("Bui Duc", "Helloooo", "XIn Chao", R.drawable.e));
        userList.add(new User("Hoang Nam", "Helloooo", "XIn Chao", R.drawable.f));


        customAdapter = new CustomAdapter(this, userList, this);
        recyclerView.setAdapter(customAdapter);

    }

    @Override
    public void onItemClicked(User myModel) {
        Toast.makeText(this, myModel.getName(), Toast.LENGTH_SHORT).show();
    }
}