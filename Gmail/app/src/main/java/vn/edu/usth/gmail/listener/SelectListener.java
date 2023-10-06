package vn.edu.usth.gmail.listener;

public interface SelectListener {
    default void onItemClicked(int position){
    }
    void onLongItemClick(int position);
}
