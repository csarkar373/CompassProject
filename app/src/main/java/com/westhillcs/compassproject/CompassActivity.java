package com.westhillcs.compassproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CompassActivity extends AppCompatActivity implements SensorEventListener{

    private ArrowView av;
    private CompassSensor accel;
    private CompassSensor geo;

    private float rotationAngleInDegrees;



    private void initializeSensors() {
        accel = new CompassSensor(Sensor.TYPE_ACCELEROMETER, this );
        geo = new CompassSensor(Sensor.TYPE_MAGNETIC_FIELD, this);
        rotationAngleInDegrees = (float)0.0;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        av = new ArrowView(this);
        this.setContentView(av);
        this.initializeSensors();

    }

    @Override
    protected void onStart() {

        super.onStart();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accel.updateSensor(event);
        geo.updateSensor(event);

        // when both sensors fire, update the rotation angle
        if (accel.fired() && geo.fired()) {

            rotationAngleInDegrees = CompassSensor.getRotationAngle(accel.getData(), geo.getData());
            av.setRotationAngle(this.rotationAngleInDegrees * -1);
            av.invalidate();
            accel.reset();
            geo.reset();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used for this app
    }




















}
