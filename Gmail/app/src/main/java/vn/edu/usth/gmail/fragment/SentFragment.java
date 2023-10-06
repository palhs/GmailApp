package vn.edu.usth.gmail.fragment;

import static vn.edu.usth.gmail.MainActivity.emailList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.usth.gmail.CustomAdapter;
import vn.edu.usth.gmail.Email_Sender;
import vn.edu.usth.gmail.R;
import vn.edu.usth.gmail.SelectListener;
import vn.edu.usth.gmail.SentAdapter;


public class SentFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    SentAdapter sentAdapter;
    String userid_sender;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sent, container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        userid_sender = currentUser.getUid();

        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid_sender).child("Sent");
        recyclerView = view.findViewById(R.id.recycler_main_sent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        emailList = new ArrayList<>();

        sentAdapter = new SentAdapter( getContext(),emailList, (SelectListener) getContext());
        recyclerView.setAdapter(sentAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Email_Sender email = dataSnapshot.getValue(Email_Sender.class);
                    emailList.add(email);
                }
                sentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}