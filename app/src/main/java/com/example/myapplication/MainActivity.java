package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.ellipse;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    JavaCameraView javaCameraView;
    Mat mat1,mat2;
    Button buttonnext;
    Scalar scalarLow,scalarHigh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenCVLoader.initDebug();

        javaCameraView = (JavaCameraView) findViewById(R.id.cameraView);
        javaCameraView.setCameraIndex(0);
        buttonnext = (Button) findViewById(R.id.button3);

        scalarLow = new Scalar(10,255,255);
        scalarHigh = new Scalar(179,255,255);
        javaCameraView.setCvCameraViewListener(this);
        javaCameraView.enableView();



        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2Activity();
            }
        });



    }



    @Override
    protected void onPause() {
        super.onPause();
        javaCameraView.disableView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        javaCameraView.enableView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        javaCameraView.disableView();
    }



    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public void onCameraViewStarted(int width, int height) {

        mat1 = new Mat(width,height, CvType.CV_16UC4);
        mat2 = new Mat(width,height, CvType.CV_16UC4);



    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        Imgproc.cvtColor(inputFrame.rgba(),mat1,Imgproc.COLOR_BGR2HSV);
        Core.inRange(mat1,scalarLow,scalarHigh,mat2);
        Mat mRgba = inputFrame.rgba();

        return mat2;
    }

    public void openMain2Activity() {

        Intent intent = new Intent(MainActivity.this, Notification.class);
        startActivity(intent);
    }

}