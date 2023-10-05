package vn.edu.usth.gmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.edu.usth.gmail.databinding.ActivityComposeBinding;

public class ComposeActivity extends AppCompatActivity {
    private ActivityComposeBinding binding;

    private DatabaseReference reference;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Change status bar background color to the corresponding color
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_all));

        binding = ActivityComposeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String sender = user.getEmail();
            binding.txtSender.setText(sender);
        }

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current user from Firebase Authentication


                String sender = binding.txtSender.getText().toString();
                String content = binding.txtContent.getText().toString();
                String subject = binding.txtSubject.getText().toString();
                String receiver = binding.txtReceiver.getText().toString();

                if (!sender.isEmpty() &&!content.isEmpty() && !subject.isEmpty() && !receiver.isEmpty()) {
                    Email email = new Email(sender, subject, content, receiver);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Email");
                    // Use push() to generate a unique key for each email
                    DatabaseReference newEmailRef = reference.push();
                    String emailKey = newEmailRef.getKey();
                    reference.child(emailKey).setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            binding.txtContent.setText("");
                            binding.txtReceiver.setText("");
                            binding.txtSubject.setText("");
                            binding.txtSender.setText("");
                            Toast.makeText(ComposeActivity.this, "Successfully Sent", Toast.LENGTH_SHORT).show();
                        }
                    });
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

        binding.linkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComposeActivity.this, "You clicked on the linkBtn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
