package vn.edu.usth.gmail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Detail_1 extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        String name = getIntent().getStringExtra("Name");
        String headMail = getIntent().getStringExtra("Head Mail");
        String content = getIntent().getStringExtra("Content");
        int image = getIntent().getIntExtra("Image", 0);

        TextView Dname = findViewById(R.id.D_name);
        TextView DheadMail = findViewById(R.id.D_head_email);
        TextView Dcontent = findViewById(R.id.D_content);
        ImageView Dimage = findViewById(R.id.D_imageview);

        Dname.setText(name);
        DheadMail.setText(headMail);
        Dcontent.setText(content);
        Dimage.setImageResource(image);

        //back to mainpage

        ImageButton backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the previous activity or fragment
                Intent intent = new Intent(Detail_1.this, MainActivity.class);
                startActivity(intent);

                // Optionally, finish the current activity to remove it from the stack
                finish();
            }
        });
    }


}
