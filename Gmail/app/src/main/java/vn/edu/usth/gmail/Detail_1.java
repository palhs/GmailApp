package vn.edu.usth.gmail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Detail_1 extends AppCompatActivity {

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
    }
}
