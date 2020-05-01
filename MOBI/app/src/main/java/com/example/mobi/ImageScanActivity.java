package com.example.mobi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.util.SparseIntArray;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ImageScanActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private TextureView cameraTextureView;
    private FrameLayout frameLayout;

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static{
        ORIENTATIONS.append(ExifInterface.ORIENTATION_NORMAL,0);
        ORIENTATIONS.append(ExifInterface.ORIENTATION_ROTATE_90,90);
        ORIENTATIONS.append(ExifInterface.ORIENTATION_ROTATE_180,180);
        ORIENTATIONS.append(ExifInterface.ORIENTATION_ROTATE_270,270);
    }

    int REQUESTCODE = 300;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagescan);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        FloatingActionButton btnShoot = (FloatingActionButton) findViewById(R.id.shoot);
        cameraTextureView = (TextureView) findViewById(R.id.cameraTextureView);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        }

        private void requestPermission(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                List<String> listPermissions = new ArrayList<>();

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    listPermissions.add(Manifest.permission.CAMERA);
                }

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    listPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }

                if (listPermissions.size() > 0){
                    String[] arrayPermissions = new String[listPermissions.size()];
                    arrayPermissions = listPermissions.toArray(arrayPermissions);
                    ActivityCompat.requestPermissions(this,arrayPermissions,0);
                } else {
                    onCheckComplete();
                    return;
                }

            }
            onCheckComplete();

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allGranted;
        if(requestCode == 0){
            allGranted = true;
            for(int grantResult : grantResults){
                if(grantResult != PackageManager.PERMISSION_GRANTED){
                    allGranted = false;
                }
            }
            if(allGranted){
                onCheckComplete();
            } else {
                finish();
            }
        }
    }

    private void onCheckComplete(){
        startCamera();
    }

    @SuppressLint("RestrictedApi")
    private void startCamera(){
        CameraX.unbindAll();

        Point __size = new Point();
        getWindowManager().getDefaultDisplay().getSize(__size);

        Rational aspectRatio = new Rational(__size.x, __size.y);
    }
}
