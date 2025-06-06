package com.example.dclassicbookhomepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewerHolder> {

    Context context;
    List<Item> items;

    public BookAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BookViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewerHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewerHolder holder, int position) {
        holder.bookTitle.setText(items.get(position).getTitle());
        holder.bookImage.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
