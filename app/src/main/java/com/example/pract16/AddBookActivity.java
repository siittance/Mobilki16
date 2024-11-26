package com.example.pract16;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddBookActivity extends AppCompatActivity {

    private EditText Name, Author;
    private Button Add;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Name = findViewById(R.id.name);
        Author = findViewById(R.id.author);
        Add = findViewById(R.id.add);

        dbHelper = new DataBaseHelper(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBookToDatabase();
            }
        });
    }

    private void addBookToDatabase() {
        String bookName = Name.getText().toString().trim();
        String bookAuthor = Author.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addBook(bookName, bookAuthor);

        if (result > 0) {
            Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddBookActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибки добавления книги", Toast.LENGTH_SHORT).show();
        }
    }
}