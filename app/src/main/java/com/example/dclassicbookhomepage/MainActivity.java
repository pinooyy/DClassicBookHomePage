package com.example.dclassicbookhomepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    Handler sliderHandler = new Handler();
    int[] storeImage = {
            R.drawable.bookstore1,
            R.drawable.bookstore2,
            R.drawable.bookstore3,
            R.drawable.bookstore4,
            R.drawable.bookstore5,
            R.drawable.bookstore6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      buat menu button
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, btnMenu);
            popup.getMenuInflater().inflate(R.menu.top_nav_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_books) {
                    startActivity(new Intent(MainActivity.this, BooksActivity.class));
                    return true;
                } else if (itemId == R.id.nav_store) {
                    startActivity(new Intent(MainActivity.this, StoreActivity.class));
                    return true;
                } else if (itemId == R.id.nav_logout) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    return true;
                } else {
                    return false;
                }

            });
            popup.show();
        });

//        Hello, User!
        TextView greetingText = findViewById(R.id.greetingText);
        String username = getIntent().getStringExtra("username");

        if (username != null && !username.isEmpty()) {
            greetingText.setText("Hello, " + username + "!");
        } else {
            greetingText.setText("Hello, User!");
        }

//      Book Featured
        RecyclerView recyclerView = findViewById(R.id.featuredView);
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Milea Suara Dari Dilan", R.drawable.fiksimileasuaradaridilan));
        items.add(new Item("Cerita yang Mekar", R.drawable.fiksiceritayangmekar));
        items.add(new Item("Cerita Negeri Dongeng", R.drawable.fiksiceritanegeridongeng));
        items.add(new Item("Si Anak Pintar", R.drawable.fiksisianakpintar));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BookAdapter(getApplicationContext(),items));

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new StoreAdapter(this, storeImage));

        TextView btnPrev = findViewById(R.id.btnPrev);
        TextView btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            int next = viewPager.getCurrentItem() + 1;
            if (next < storeImage.length) {
                viewPager.setCurrentItem(next);
            }
        });

        btnPrev.setOnClickListener(v -> {
            int prev = viewPager.getCurrentItem() - 1;
            if (prev >= 0) {
                viewPager.setCurrentItem(prev);
            }
        });

        // Auto-slide every 4 seconds
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(slideRunnable);
                sliderHandler.postDelayed(slideRunnable, 4000); // 4 seconds
            }
        });
    }

    private final Runnable slideRunnable = () -> {
        int nextItem = (viewPager.getCurrentItem() + 1) % storeImage.length;
        viewPager.setCurrentItem(nextItem);
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(slideRunnable, 4000);
    }
}