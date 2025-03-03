package edu.sjsu.android.project2misbahsyed;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Sensor sensor;
    private SensorManager manager;
    private MyListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        MyView myView = new MyView(this);
        setContentView(myView);
        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null){
            Toast.makeText(this, "No accelerometer", Toast.LENGTH_SHORT).show();
            return;
        }
        listener = myView.getListener();
    }

    //App must register listener for sensor event - register in activity onStart() and onStop()
    @Override
    public void onStart(){
        super.onStart();
        if(sensor != null) {
            manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }
    protected void onStop(){
        super.onStop();
        manager.unregisterListener(listener);
    }


}