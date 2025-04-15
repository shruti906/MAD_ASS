package com.example.photosgalleryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity"; // Tag for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gridView = findViewById(R.id.galleryGridView);

        // Get the directory where photos are stored
        File directory = getExternalFilesDir("Photos");

        // Log the directory path
        if (directory != null) {
            Log.d(TAG, "Photos directory: " + directory.getAbsolutePath());
        } else {
            Log.e(TAG, "Failed to get the Photos directory.");
        }

        if (directory == null || !directory.exists()) {
            Toast.makeText(this, "No photos found!", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "Directory does not exist or is null.");
            return;
        }

        File[] files = directory.listFiles();

        // Log the number of files found
        if (files != null) {
            Log.d(TAG, "Number of files found: " + files.length);
        } else {
            Log.w(TAG, "No files found in the directory.");
        }

        if (files == null || files.length == 0) {
            Toast.makeText(this, "No photos found!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set up the GridView with an adapter
        GalleryAdapter adapter = new GalleryAdapter(this, files);
        gridView.setAdapter(adapter);
        Log.d(TAG, "Adapter set with files.");

        // Handle image clicks to show details
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                File selectedImage = files[position];
                Log.d(TAG, "Image clicked: " + selectedImage.getName());

                Intent intent = new Intent(GalleryActivity.this, ImageDetailsActivity.class);
                intent.putExtra("imagePath", selectedImage.getAbsolutePath());
                startActivity(intent);
            }
        });
    }
}
