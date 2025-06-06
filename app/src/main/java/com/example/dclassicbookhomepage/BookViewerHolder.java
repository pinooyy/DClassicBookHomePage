package com.example.dclassicbookhomepage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewerHolder extends RecyclerView.ViewHolder {

    ImageView bookImage;
    TextView bookTitle;
    public BookViewerHolder(@NonNull View itemView) {
        super(itemView);
        bookImage = itemView.findViewById(R.id.bookImage);
        bookTitle = itemView.findViewById(R.id.bookTitle);
    }
}
