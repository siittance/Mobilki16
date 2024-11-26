package com.example.pract16;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private EditText editName, editAuthor;

    private Button btndelete, save;

    private int bookId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dbHelper = new DataBaseHelper(this);

        editName = findViewById(R.id.editTextName);
        editAuthor = findViewById(R.id.editTextAuthor);
        btndelete = findViewById(R.id.delete);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("BOOK_ID", -1);
        String bookName = intent.getStringExtra("BOOK_NAME");
        String bookAuthor = intent.getStringExtra("BOOK_AUTHOR");

        editName.setText(bookName);
        editAuthor.setText(bookAuthor);

        btndelete.setOnClickListener(v -> {
            dbHelper.deleteBookById(bookId);
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
            finish();
        });

        save = findViewById(R.id.save);
        save.setOnClickListener(v -> {
            String updatedName = editName.getText().toString();
            String updatedAuthor = editAuthor.getText().toString();

            if (!updatedName.isEmpty() && !updatedAuthor.isEmpty()) {
                dbHelper.updateBook(bookId, updatedName, updatedAuthor);
                finish();
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });
    }
}