package com.example.photosgalleryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class ImageDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        ImageView imageView = findViewById(R.id.imageView);
        TextView imageDetails = findViewById(R.id.imageDetails);
        Button deleteButton = findViewById(R.id.deleteButton);

        String imagePath = getIntent().getStringExtra("imagePath");
        File imageFile = new File(imagePath);

        imageView.setImageURI(android.net.Uri.fromFile(imageFile));

        String details = "Name: " + imageFile.getName() + "\nPath: " + imagePath +
                "\nSize: " + imageFile.length() + " bytes\nLast Modified: " + new java.util.Date(imageFile.lastModified());
        imageDetails.setText(details);

        deleteButton.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure you want to delete this image?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    if (imageFile.delete()) {
                        Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to delete image", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show());
    }
}
