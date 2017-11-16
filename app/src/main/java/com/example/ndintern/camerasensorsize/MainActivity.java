package com.example.ndintern.camerasensorsize;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SizeF;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final CameraCharacteristics.Key SENSOR_INFO_PHYSICAL_SIZE=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView BackCamera = (TextView)findViewById(R.id.textView0);
        TextView FrontCamera = (TextView)findViewById(R.id.textView1);
        BackCamera.setText("  Back Camera: "+getCameraResolution(0) + " mm");
        FrontCamera.setText("Front Camera: " + getCameraResolution(1) + " mm");

    }
    private SizeF getCameraResolution(int camNum)
    {
        SizeF size = new SizeF(0,0);
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String[] cameraIds = manager.getCameraIdList();
            if (cameraIds.length > camNum) {
                CameraCharacteristics character = manager.getCameraCharacteristics(cameraIds[camNum]);
                size = character.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
                if (character.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT){
                    Log.v("TAG","FRONT CAMERA " + size);
                }
                if(character.get(CameraCharacteristics.LENS_FACING)== CameraCharacteristics.LENS_FACING_BACK){
                    Log.v("TAG","BACK CAMERA " + size);
                }
            }
        }
        catch (CameraAccessException e)
        {
            Log.e("YourLogString", e.getMessage(), e);
        }

        return size;
    }
}
