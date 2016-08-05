package com.westhillcs.compassproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

/**
 * Created by Chandan on 6/5/2016.
 */
public class CompassSensor {

    // data for each sensor
    private final Sensor sensor;
    private float [] sensorData;
    private final int myType;
    private boolean hasFired;

    // sensor manager and its data
    private static SensorManager sm;
    private static float [] rotationMatrix;
    private static float [] orientationMatrix;

    public CompassSensor(int myType, Context parent) {

        // only create Sensor Manager (and its data) once
        if (sm == null) {
            sm = (SensorManager)parent.getSystemService(parent.SENSOR_SERVICE);
            rotationMatrix = new float [9];
            orientationMatrix = new float[3];
        }

        // initialize sensor data
        this.myType = myType;
        this.sensor = sm.getDefaultSensor(this.myType);
        sm.registerListener((SensorEventListener)parent, this.sensor, SensorManager.SENSOR_DELAY_NORMAL);
        this.sensorData = new float[3];
        this.hasFired = false;
    }
    public boolean fired() { return hasFired;  }

    public float [] getData() {  return sensorData;  }

    public void reset() {
        this.hasFired = false;
    }

    public void updateSensor(SensorEvent event) {
        if (event.sensor.getType() == myType) {
            System.arraycopy(event.values, 0, sensorData, 0, 3);
            this.hasFired = true;
        }
    }

    // returns rotation angle in degrees
    public static float getRotationAngle(float [] a, float [] b) {
        float returnValue = 0;
        boolean valid = SensorManager.getRotationMatrix(CompassSensor.rotationMatrix, null, a, b);
        // if we succeeded in loading the rotation matrix, load the orientation matrix & rotation angle
        if (valid) {
            SensorManager.getOrientation(CompassSensor.rotationMatrix, CompassSensor.orientationMatrix);
            returnValue = (float)Math.toDegrees(CompassSensor.orientationMatrix[0]);
        }
        return returnValue;
    }

}
