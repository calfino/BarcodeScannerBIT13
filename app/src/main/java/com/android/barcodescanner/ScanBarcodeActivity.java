package com.android.barcodescanner;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.Result;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarcodeActivity extends Activity implements ZXingScannerView.ResultHandler{

    SurfaceView cameraResultView;
    ZXingScannerView mScannerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_barcode);
//
//        cameraResultView = (SurfaceView)findViewById(R.id.cameraPreview);
//        createCameraSource();
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void handleResult(Result result) {
//        Log.v("TAG", result.getText()); // Prints scan results
//        Log.v("TAG", result.getBarcodeFormat().toString());
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scan Result");
//        builder.setMessage(result.getText());
//        AlertDialog alert1 = builder.create();
//        alert1.show();

        if(result.getText() != null) {
            Intent intent = new Intent();
            intent.putExtra("barcode", result.getText());
            setResult(CommonStatusCodes.SUCCESS, intent);
            finish();
        }
    }
}
