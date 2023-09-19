package vn.edu.usth.gmail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    Context context;
    List<User> list;
    private final SelectListener listener;
    public CustomAdapter(Context context, List<User> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where you inflate the layout (Giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item,parent,false);
        return new CustomViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder,  int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        holder.textName.setText(list.get(position).getName());
        holder.textHmail.setText(list.get(position).getHead_mail());
        holder.textContent.setText(list.get(position).getContent());
        holder.imageView.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // grabbing the view from our recycler_view_row layout file
        // kinda like in the onCreate method
        public TextView textName, textHmail, textContent;
        public ImageView imageView;
        public CardView cardView;
        public CustomViewHolder(@NonNull View itemView, SelectListener listener) {
            super(itemView);
            textName = itemView.findViewById(R.id.name);
            textHmail = itemView.findViewById(R.id.head_email);
            textContent = itemView.findViewById(R.id.content);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.main_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onItemClicked(pos);
                        }
                    }
                }
            });
        }

    }

}
