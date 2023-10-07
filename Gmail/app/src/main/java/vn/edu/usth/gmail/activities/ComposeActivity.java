package vn.edu.usth.gmail.activities;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import vn.edu.usth.gmail.Email_Sender;
import vn.edu.usth.gmail.R;
import vn.edu.usth.gmail.databinding.ActivityComposeBinding;

public class ComposeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private ActivityComposeBinding binding;

    private DatabaseReference Sender_reference;
    private DatabaseReference Receiver_reference;
    private FirebaseDatabase db;
    FirebaseAuth mAuth;
    public FirebaseUser firebaseUser;
    private String userid_sender;
    private String userid_receiver;
    DatabaseReference newEmailRefSender;
    DatabaseReference newEmailRefReceiver;
    String emailKeySender;
    String emailKeyReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Change status bar background color to the corresponding color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_all));

        binding = ActivityComposeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        userid_sender = firebaseUser.getUid();



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String sender = user.getEmail();
            binding.txtSender.setText(sender);
        }

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = binding.txtContent.getText().toString();
                String subject = binding.txtSubject.getText().toString();
                String receiver = binding.txtReceiver.getText().toString();
                String sender = binding.txtSender.getText().toString();


                String targetEmail = receiver;

                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                Query query = usersRef.orderByChild("email").equalTo(targetEmail);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            // Retrieve the userid based on the username
                            userid_receiver = userSnapshot.getKey();
                            // Now you have the userid corresponding to the desired username
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                    }
                });



                // Get the current user from Firebase Authentication
                if (!sender.isEmpty() &&!content.isEmpty() && !subject.isEmpty() && !receiver.isEmpty()) {
                    Email_Sender emailSender = new Email_Sender(sender, subject, content, receiver);
                    db = FirebaseDatabase.getInstance();
                    Sender_reference = db.getReference("Users");
                    Receiver_reference = db.getReference("Users");
                    // Use push() to generate a unique key for each email

                    String emailKey = Sender_reference.child(userid_sender).child("Sent").push().getKey();

                    Sender_reference.child(userid_sender).child("Sent").child(emailKey).setValue(emailSender).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            Sender_reference.child(userid_receiver).child("Inbox").child(emailKey).setValue(emailSender);
                            binding.txtContent.setText("");
                            binding.txtReceiver.setText("");
                            binding.txtSubject.setText("");
                            binding.txtSender.setText("");
                            Toast.makeText(ComposeActivity.this, "Successfully Sent", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(ComposeActivity.this,"Please fill all the boxes",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle back button click
        binding.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the previous activity or fragment
                Intent intent = new Intent(ComposeActivity.this, MainActivity.class);
                startActivity(intent);

                // Optionally, finish the current activity to remove it from the stack
                finish();
            }
        });

        // Add click listeners for moreBtn and linkBtn as needed
        // For example:
        binding.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComposeActivity.this, "You clicked on the moreBtn", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton popupButton3 = findViewById(R.id.moreBtn);
        popupButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreBtnPopup(view);
            }
        });

        binding.linkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComposeActivity.this, "You clicked on the linkBtn", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moreBtnPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.more_compose);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}