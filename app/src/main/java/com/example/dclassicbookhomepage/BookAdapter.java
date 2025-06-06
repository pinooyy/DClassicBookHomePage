package com.example.dclassicbookhomepage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    Context context;
    List<Item> items;

    public BookAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Pastikan layout yang dipakai adalah layout yang benar (sesuaikan dengan layout XML kamu)
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bookTitle.setText(item.getTitle());
        holder.bookImage.setImageResource(item.getImage()); // Sesuaikan method getImage() atau getImageResId()

        // Click listener untuk membuka halaman detail
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Kalau context bukan activity
            intent.putExtra("bookTitle", item.getTitle());
            intent.putExtra("bookImage", item.getImage());
            intent.putExtra("bookAuthor", "- Default Author -"); // Bisa diganti dengan data author sebenarnya
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle;
        ImageView bookImage;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookImage = itemView.findViewById(R.id.bookImage);
        }
    }
}
