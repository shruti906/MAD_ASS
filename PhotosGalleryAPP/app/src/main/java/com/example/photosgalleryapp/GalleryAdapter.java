package com.example.photosgalleryapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.io.File;

public class GalleryAdapter extends BaseAdapter {
    private Context context;
    private File[] images;

    public GalleryAdapter(Context context, File[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = convertView == null ? new ImageView(context) : (ImageView) convertView;
        imageView.setImageURI(android.net.Uri.fromFile(images[position]));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
        return imageView;
    }
}
