package com.example.skystonedetector;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenCVLoader.initDebug();

        Mat img = null;

        try {
            img = Utils.loadResource(getApplicationContext(), R.drawable.stones);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGRA);
        Bitmap img_bitmap = Bitmap.createBitmap(img.cols(), img.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(img, img_bitmap);
        ImageView imageView = findViewById(R.id.img);
        imageView.setImageBitmap(img_bitmap);
    }

    public void identifySkyStone(View v) {
        Mat img = null;

        try {
            img = Utils.loadResource(getApplicationContext(), R.drawable.stones);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGRA);


        SkyStoneImageProcessor pipeline = new SkyStoneImageProcessor();
        int stonePosition = pipeline.process(img);

      }


}