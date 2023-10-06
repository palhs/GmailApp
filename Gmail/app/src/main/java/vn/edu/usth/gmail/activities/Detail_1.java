package vn.edu.usth.gmail.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.gmail.Email_Sender;
import vn.edu.usth.gmail.listener.OnSwipeTouchListener;
import vn.edu.usth.gmail.R;

public class Detail_1 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    DatabaseReference database;
    String userid_sender;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @SuppressLint("RestrictedApi")
    private boolean isStarSelected = false; // Variable to track star selection

    private List<Email_Sender> emailSenderList = new ArrayList<>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Change status bar background to the corresponding
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_all));
        setContentView(R.layout.activity_detail1);


        emailSenderList=loadEmailData();

        int position = getIntent().getIntExtra("position", 0);
        final int[] currentIndex = {position};

//         Retrieve the email data based on the position
        String name = emailSenderList.get(position).getSender();
        String headMail = emailSenderList.get(position).getSubject();
        String content = emailSenderList.get(position).getContent();
        String receiver = emailSenderList.get(position).getReceiver();

        // Update the UI elements with the email data
        TextView Dname = findViewById(R.id.D_name);
        TextView DheadMail = findViewById(R.id.D_head_email);
        TextView Dcontent = findViewById(R.id.D_content);
        TextView Dreceiver = findViewById(R.id.toW);

        Dname.setText(name);
        DheadMail.setText(headMail);
        Dcontent.setText(content);
        Dreceiver.setText(receiver);


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

        //popup row_down
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton popupButton = findViewById(R.id.row_down);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        //popup more
        ImageButton popupButton2 = findViewById(R.id.more);
        popupButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morePopup(view);
            }
        });

        //popup more_vert
        ImageButton popupButton3 = findViewById(R.id.more_vert);
        popupButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morevertPopup(view);
            }
        });

        // swipe to left or right
        LinearLayout swappable = findViewById(R.id.detail1);
        swappable.setOnTouchListener(new OnSwipeTouchListener(Detail_1.this) {

        public void onSwipeRight() {
                // Swipe right
            if (currentIndex[0] > 0) {
                currentIndex[0]--;

                Dname.setText(emailSenderList.get(currentIndex[0]).getSender());
                DheadMail.setText(emailSenderList.get(currentIndex[0]).getSubject());
                Dcontent.setText(emailSenderList.get(currentIndex[0]).getContent());
//                Dimage.setImageResource(emailList.get(currentIndex[0]).getImage());
            }
        }
            public void onSwipeLeft() {
                // Swipe left
                if (currentIndex[0] < emailSenderList.size() - 1) {
                    currentIndex[0]++;

                    Dname.setText(emailSenderList.get(currentIndex[0]).getSender());
                    DheadMail.setText(emailSenderList.get(currentIndex[0]).getSubject());
                    Dcontent.setText(emailSenderList.get(currentIndex[0]).getContent());
                }
            }
        });
    }

    private List<Email_Sender> loadEmailData() {

//        mAuth = FirebaseAuth.getInstance();
//        currentUser = mAuth.getCurrentUser();
//        userid_sender = currentUser.getUid();
//        database = FirebaseDatabase.getInstance().getReference().child("Users").child(userid_sender).child("Sent");
//        database.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                emailSenderList.clear(); // Clear the list before adding new data
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Email_Sender email = dataSnapshot.getValue(Email_Sender.class);
//                    emailSenderList.add(email);
//                }
//                // Now, emailSenderList contains the data from Firebase
//                // You can update your UI or adapter here
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle errors
//            }
//        });

//        // Add email data to the list
        emailSenderList.add(new Email_Sender("hellobaby@gmail.com", "Hello World", "Go out tonight?",  "me"));
        emailSenderList.add(new Email_Sender("hellobaby@gmail.com", "CHECK ATTENDANCE LIST", "Find your name in the Excel file below", "me"));
        emailSenderList.add(new Email_Sender("hellobaby@gmail.com", "INTERNSHIP", "Urgent to find a company", "me"));
//        emailList.add(new Email("Hoang Thi Van Anh", "Machine Learning Checklist Attendance", "Dear all, the ICT Department", R.drawable.a,"Sep 21"));
//        emailList.add(new Email("Bui Duc", "GPA", "How about you?", R.drawable.e,"Sep 20"));
//        emailList.add(new Email("Google", "Security Alert", "A new signing on Iphone 15", R.drawable.e,"Sep 20"));
//        emailList.add(new Email("Trinh Thi Thu Trang", "Đăng kí môn lựa chọn", "Dear students", R.drawable.f,"Sep 19"));
//        emailList.add(new Email("Trong Duc", "Retake ADS", "Can you go retake with me?", R.drawable.c,"Sep 19"));
//        emailList.add(new Email("USTH Student Association", "Mid-Autumn Festival ", "Do nothing", R.drawable.a,"Sep 18"));
//        emailList.add(new Email("USTH Student Services", "New Member Recruitment", "Nobody wants to join", R.drawable.b,"Sep 17"));
//        emailList.add(new Email("Trong Duc", "Best TFT Comps", "Học hỏi đi", R.drawable.c,"Sep 15"));
//        emailList.add(new Email("Google", "Security Alert", "A new signing on Iphone 15", R.drawable.e,"Sep 13"));
//        emailList.add(new Email("Student Services", "PHÒNG CHÁY CHỮA CHÁY", "Thân gửi các em sinh viên", R.drawable.f,"Sep 12"));
//        emailList.add(new Email("Hoang Thi Van Anh", "OOAD Student Checklish", "Dear Students,", R.drawable.a, "Sep 11"));
//        emailList.add(new Email("Trinh Thi Thu Trang", "Web Applied Developments", "Find your name in the Excel file below", R.drawable.b, "Sep 10"));
//        emailList.add(new Email("Trong Duc", "SOS", "Bài này khó", R.drawable.c,"Sep 9"));
//        emailList.add(new Email("Bui Duc", "Hi", "Let's play basketball", R.drawable.e,"Sep 8"));
//        emailList.add(new Email("Hoang Nam", "Helloooo", "Welcome!!", R.drawable.f,"Sep 8"));
//        emailList.add(new Email("USTH Student Services", "Đăng kí gian hàng hội chợ", "Thân gửi em, để chào mừng tân sinh viên ", R.drawable.a,"Sep 7"));
//        emailList.add(new Email("GED Dept", "CHECK ATTENDANCE LIST", "Find your name in the Excel file below", R.drawable.f,"Sep 7"));
//        emailList.add(new Email("Phan Anh", "INTERNSHIP", "Urgent", R.drawable.a,"Sep 6"));
//        emailList.add(new Email("Hoang Thi Van Anh", "Machine Learning Checklist Attendance", "Dear all, the ICT Department", R.drawable.a,"Sep 5"));
//        emailList.add(new Email("Bui Duc", "GPA", "How about you?", R.drawable.e,"Sep 5"));
//        emailList.add(new Email("Google", "Security Alert", "A new signing on Iphone 15", R.drawable.e,"Sep 4"));
//        emailList.add(new Email("Trinh Thi Thu Trang", "Đăng kí môn lựa chọn", "Dear students", R.drawable.f,"Sep4"));
//        emailList.add(new Email("Trong Duc", "Retake ADS", "Can you go retake with me?", R.drawable.c,"Sep 3"));
//        emailList.add(new Email("USTH Student Association", "Mid-Autumn Festival ", "Do nothing", R.drawable.a,"Sep 2"));
//        emailList.add(new Email("USTH Student Services", "New Member Recruitment", "Nobody wants to join", R.drawable.b,"Sep 1"));
//        emailList.add(new Email("Trong Duc", "Best TFT Comps", "Học hỏi đi", R.drawable.c,"Sep 1"));
//        emailList.add(new Email("Google", "Security Alert", "A new signing on Iphone 15", R.drawable.e,"Aug 31"));
//        emailList.add(new Email("Student Services", "PHÒNG CHÁY CHỮA CHÁY", "Thân gửi các em sinh viên", R.drawable.f,"Sep 31"));
//        emailList.add(new Email("Hoang Thi Van Anh", "OOAD Student Checklish", "Dear Students,", R.drawable.a,"Sep 30"));
//        emailList.add(new Email("Trinh Thi Thu Trang", "Web Applied Developments", "Find your name in the Excel file below", R.drawable.b,"Sep 29"));
//        emailList.add(new Email("Trong Duc", "SOS", "Bài này khó", R.drawable.c,"Sep 28"));
//        emailList.add(new Email("Bui Duc", "Hi", "Let's play basketball", R.drawable.e,"Sep 28"));
//        emailList.add(new Email("Hoang Nam", "Helloooo", "Welcome!!", R.drawable.f,"Sep 27"));

        return emailSenderList;
    }


    // change color star when click on
    private boolean liked = false;
    public void star(View view){
        ImageButton button = (ImageButton) view;
        int icon;

        if (liked) {
            liked = false;
            icon = R.drawable.star_white;
        }
        else {
            liked = true;
            icon = R.drawable.star_yellow;
        }

        button.setBackgroundResource(icon);

    }
    public void morePopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.options_head);
        popup.show();
    }

    public void showPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_row_down);
        popup.show();
    }

    public void morevertPopup(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.options_more_vert);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.from_popup) {
            Toast.makeText(this, "From: hoangnam@gmail.com", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.reply_popup) {
            Toast.makeText(this, "Reply-To: quangdung@gmail.com", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.Bcc_popup) {
            Toast.makeText(this, "Bcc: bi12-ict@st.usth.edu.vn", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.date_popup) {
            Toast.makeText(this, "Date: Sep 20, 2023, 10:12 AM", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }
}
