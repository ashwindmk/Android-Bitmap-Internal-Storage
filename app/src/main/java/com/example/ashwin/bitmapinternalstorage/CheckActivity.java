package com.example.ashwin.bitmapinternalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE_PERMISSION = 102;

    private ImageView mImageView;
    private Button mLoadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        initViews();
    }

    private void initViews() {
        mImageView = (ImageView) findViewById(R.id.imageView);

        mLoadButton = (Button) findViewById(R.id.loadButton);
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(CheckActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CheckActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
                } else {
                    loadImage();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {
                // If request is cancelled, the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    loadImage();
                } else {
                    // permission denied, boo!
                    Toast.makeText(CheckActivity.this, "Image cannot be loaded.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void loadImage() {
        Bitmap bitmap = ImageUtils.loadImageBitmap(CheckActivity.this, "image_1", "jpg");
        mImageView.setImageBitmap(bitmap);
        if (bitmap == null) {
            Toast.makeText(CheckActivity.this, "No image found", Toast.LENGTH_SHORT).show();
        }
    }
}
