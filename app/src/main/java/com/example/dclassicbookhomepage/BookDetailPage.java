package com.example.dclassicbookhomepage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BookDetailPage extends AppCompatActivity {

    ImageView bookCover;
    TextView bookTitle, bookAuthor;
    EditText addressField, phoneField;
    Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_page);

        // Inisialisasi komponen UI
        bookCover = findViewById(R.id.bookCover);
        bookTitle = findViewById(R.id.bookTitle);
        bookAuthor = findViewById(R.id.bookAuthor);
        addressField = findViewById(R.id.addressField);
        phoneField = findViewById(R.id.phoneField);
        buyButton = findViewById(R.id.buyButton);

        // Ambil data dari Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("bookTitle");
        int imageResId = intent.getIntExtra("bookImage", R.drawable.fiksiceritanegeridongeng);
        String author = intent.getStringExtra("bookAuthor");

        // Set data ke view
        bookTitle.setText(title != null ? title : "- No Title -");
        bookCover.setImageResource(imageResId);
        bookAuthor.setText(author != null ? author : "- Unknown -");

        // Tombol Back
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> onBackPressed());

        // Menu popup
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(view -> {
            PopupMenu popup = new PopupMenu(BookDetailPage.this, btnMenu);
            popup.getMenuInflater().inflate(R.menu.top_nav_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_books) {
                    startActivity(new Intent(BookDetailPage.this, BooksActivity.class));
                    return true;
                } else if (itemId == R.id.nav_store) {
                    startActivity(new Intent(BookDetailPage.this, StoreActivity.class));
                    return true;
                } else if (itemId == R.id.nav_logout) {
                    startActivity(new Intent(BookDetailPage.this, LoginActivity.class));
                    finish();
                    return true;
                } else {
                    return false;
                }
            });
            popup.show();
        });


        // Validasi dan aksi tombol BUY
        buyButton.setOnClickListener(v -> {
            String address = addressField.getText().toString().trim();
            String phone = phoneField.getText().toString().trim();

            if (address.isEmpty() || phone.isEmpty()) {
                new MaterialAlertDialogBuilder(BookDetailPage.this)
                        .setTitle("Error")
                        .setMessage("Address and phone number must be filled.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            if (!phone.matches("\\d+")) {
                new MaterialAlertDialogBuilder(BookDetailPage.this)
                        .setTitle("Error")
                        .setMessage("Phone number must be numeric.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            new MaterialAlertDialogBuilder(BookDetailPage.this)
                    .setTitle("Success")
                    .setMessage("Confirmation email has been sent.")
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .show();
        });
    }
}
