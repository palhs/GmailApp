package vn.edu.usth.gmail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView textName, textHmail, textContent;
    public ImageView imageView;

    public CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.name);
        textHmail = itemView.findViewById(R.id.head_email);
        textContent = itemView.findViewById(R.id.content);
        imageView = itemView.findViewById(R.id.imageview);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
