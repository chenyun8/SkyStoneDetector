package com.example.skystonedetector;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int randomInteger = new Random().nextInt(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenCVLoader.initDebug();

        Mat img = null;
        Mat img1 = null;
        Mat img2 = null;
        Mat img3 = null;

        try {
            img1 = Utils.loadResource(getApplicationContext(), R.drawable.stones1);
            img2 = Utils.loadResource(getApplicationContext(), R.drawable.stones2);
            img3 = Utils.loadResource(getApplicationContext(), R.drawable.stones3);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(randomInteger == 1){
            img = img1;
        }else if(randomInteger ==2){
            img = img2;
        }else{
            img = img3;
        }


        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGRA);

        setImage(img);
    }

    public void setImage(Mat img) {
        Bitmap img_bitmap = Bitmap.createBitmap(img.cols(), img.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(img, img_bitmap);
        ImageView imageView = findViewById(R.id.img);
        imageView.setImageBitmap(img_bitmap);
    }


    public void identifySkyStone(View v) {
        Mat img = null;
        Mat img1 = null;
        Mat img2 = null;
        Mat img3 = null;

        try {
            img1 = Utils.loadResource(getApplicationContext(), R.drawable.stones1);
            img2 = Utils.loadResource(getApplicationContext(), R.drawable.stones2);
            img3 = Utils.loadResource(getApplicationContext(), R.drawable.stones3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(randomInteger == 1){
            img = img1;
        }else if(randomInteger ==2){
            img = img2;
        }else{
            img = img3;
        }

        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGRA);

        SkyStoneImageProcessor pipeline = new SkyStoneImageProcessor(this);
        int stonePosition = pipeline.process(img);

        TextView textView = findViewById(R.id.stonePosition);
        String output = getResources().getString(R.string.stone_position);
        textView.setText(output + ": " + stonePosition );
      }
}