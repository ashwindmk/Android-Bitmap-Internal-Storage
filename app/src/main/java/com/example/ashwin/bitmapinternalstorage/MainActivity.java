package com.example.ashwin.bitmapinternalstorage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE_PERMISSION = 101;

    private ImageView mImageView;
    private Button mSaveButton, mCheckButton, mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mImageView = (ImageView) findViewById(R.id.imageView);

        mSaveButton = (Button) findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
                } else {
                    saveImage();
                }
            }
        });

        mCheckButton = (Button) findViewById(R.id.checkButton);
        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                startActivity(intent);
            }
        });

        mDeleteButton = (Button) findViewById(R.id.deleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteImage();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {
                // If request is cancelled, the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    saveImage();
                } else {
                    // permission denied, boo!
                    Toast.makeText(MainActivity.this, "Image cannot be saved.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void saveImage() {
        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ImageUtils.saveImageBitmap(MainActivity.this, bitmap, "image_1", "jpg");
    }

    private void deleteImage() {
        ImageUtils.deleteImageBitmap(MainActivity.this, "image_1", "jpg");
    }
}
