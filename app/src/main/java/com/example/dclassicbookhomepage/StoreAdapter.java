package com.example.dclassicbookhomepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private Context context;
    private int[] storeImages;

    public StoreAdapter(Context context, int[] storeImages) {
        this.context = context;
        this.storeImages = storeImages;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.store_slider_item, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.imageView.setImageResource(storeImages[position]);
    }

    @Override
    public int getItemCount() {
        return storeImages.length;
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderImage);
        }
    }
}
