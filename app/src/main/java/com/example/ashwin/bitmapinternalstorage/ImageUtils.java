package com.example.ashwin.bitmapinternalstorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ashwin on 22/9/17.
 */

public class ImageUtils {

    public static void saveImageBitmap(Context context, Bitmap bitmap, String name, String extension){
        name = name + "." + extension;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap loadImageBitmap(Context context, String name, String extension){
        name = name + "." + extension;
        FileInputStream fileInputStream = null;
        Bitmap bitmap = null;
        try {
            fileInputStream = context.openFileInput(name);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static void deleteImageBitmap(Context context, String name, String extension) {
        name = name + "." + extension;
        context.deleteFile(name);
    }

}
