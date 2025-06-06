package com.example.dclassicbookhomepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dclassicbookhomepage.BookAdapter;
import com.example.dclassicbookhomepage.MainActivity;
import com.example.dclassicbookhomepage.R;
import com.example.dclassicbookhomepage.adapter.BookAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class BooksActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private BookAdapter pagerAdapter;
    private Button btnMenu;
    private LinearLayout dashboardMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new BookAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Fiction" : "Non-Fiction")
        ).attach();

        // Dashboard menu logic
        btnMenu = findViewById(R.id.btnMenu); // Button, bukan ImageButton
        dashboardMenu = findViewById(R.id.dashboardMenu);

        btnMenu.setOnClickListener(v -> {
            if (dashboardMenu.getVisibility() == View.GONE) {
                dashboardMenu.setVisibility(View.VISIBLE);
            } else {
                dashboardMenu.setVisibility(View.GONE);
            }
        });

        // Navigasi tombol menu dashboard
        findViewById(R.id.btnHome).setOnClickListener(v ->
                startActivity(new Intent(this, HomeActivity.class)));

        findViewById(R.id.btnOurStores).setOnClickListener(v -> {
            startActivity(new Intent(this, OurStoreActivity.class));
            dashboardMenu.setVisibility(View.GONE);
        });

        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
