package com.tesseract.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.tesseract.myorintationlib.MyRotationTest;

public class RotationVactorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_vactor);
        TextView textView = findViewById(R.id.tvRotationData);
        MyRotationTest myRotationTest = MyRotationTest.getInstance();
        myRotationTest.getSystemServices(this);
        textView.setText(myRotationTest.readSensorData());
    }
}