package edu.sjsu.android.project2misbahsyed;

import static android.view.Surface.*;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.display.DisplayManager;
import android.view.Display;
import java.sql.Timestamp;

public class MyListener implements SensorEventListener{
    private Display display;
    private float x, y;
    private long timestamp;

    public MyListener(Context context) {
        // implement feb20- Initialize the Display object using the context object passed
        display = ((DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE)).getDisplay(Display.DEFAULT_DISPLAY);
        x = 0f;
        y = 0f;
        timestamp = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        //implement Feb 20
        timestamp = sensorEvent.timestamp;
        int rotation = display.getRotation();
        if (display.getRotation() == ROTATION_0){
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
        }  else if (display.getRotation() == ROTATION_90){
            x = -sensorEvent.values[1];
            y = sensorEvent.values[0];
        } else if (display.getRotation() == ROTATION_180){
            x = -sensorEvent.values[0];
            y = -sensorEvent.values[1];
        } else if (display.getRotation() == ROTATION_270){
            x = sensorEvent.values[1];
            y = -sensorEvent.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public long getTimestamp(){
        return timestamp;
    }
}
