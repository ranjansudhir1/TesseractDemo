package com.tesseract.myorintationlib;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyRotationTest extends Activity implements SensorEventListener {

    public static MyRotationTest INSTANCE;
    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private static final int INTERVAL = 8 * 1000;
    private String sensorData = "Test Data";

    public static MyRotationTest getInstance() {
        if (INSTANCE == null) {
            return new MyRotationTest();
        }
        return INSTANCE;
    }

    public void getSystemServices(Context context) {
        try {
            sensorManager = (SensorManager) context.getSystemService(Activity.SENSOR_SERVICE);
            rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(this, rotationSensor, INTERVAL);
        } catch (Exception e) {
            //Do Nothing
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == rotationSensor) {
            if (event.values.length > 4) {
                float[] rotationVector = new float[4];
                System.arraycopy(event.values, 0, rotationVector, 0, 4);
                sensorData = getData(rotationVector);
            } else {
                sensorData = getData(event.values);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Do Nothing
    }

    private String getData(float[] vectors) {
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, vectors);
        int X = SensorManager.AXIS_X;
        int Z = SensorManager.AXIS_Z;
        float[] matrix = new float[9];
        SensorManager.remapCoordinateSystem(rotationMatrix, X, Z, matrix);
        float[] orientation = new float[3];
        SensorManager.getOrientation(matrix, orientation);
        float orientation_one = orientation[1];
        float orientation_two = orientation[2];
        return orientation_one + "/" + orientation_two;
    }

    public String readSensorData() {
        return sensorData;
    }

}
