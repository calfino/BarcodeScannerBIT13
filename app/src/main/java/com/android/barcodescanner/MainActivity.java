package com.android.barcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.common.api.CommonStatusCodes;

public class MainActivity extends AppCompatActivity {

    EditText barcodeResultTxt;
    EditText modelBarcodeResultTxt;
    EditText wlanBarcodeResultTxt;
    ImageView imageViewer;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        type = "";
        barcodeResultTxt = (EditText) findViewById(R.id.serialBarcodeResult);
        modelBarcodeResultTxt = (EditText) findViewById(R.id.modelBarcodeResult);
        wlanBarcodeResultTxt = (EditText) findViewById(R.id.wlanBarcodeResult);
        imageViewer = (ImageView) findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.containsKey("uriImage")){
            String temp = extras.getString("uriImage");
            imageViewer.setImageURI(Uri.parse(temp));
        }
    }

    public void scanModelBarcodeOnClick(View v){
        type = "Model";
        scanActivity(v);
    }

    public void scanSerialBarcodeOnClick(View v){
        type = "Serial";
        scanActivity(v);
    }

    public void scanWlanBarcodeOnClick(View v){
        type = "WLAN";
        scanActivity(v);
    }

    private void scanActivity(View v){
        Intent intent = new Intent(this,ScanBarcodeActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String value;
        if(requestCode == 0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    value = data.getStringExtra("barcode");
                }else{
                    value = "";
                }
                insertText(value);
            }
        }else{

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertText(String s){
        if(type.equalsIgnoreCase("Serial")){
            barcodeResultTxt.setText(s);
        }else if(type.equalsIgnoreCase("Model")){
            modelBarcodeResultTxt.setText(s);
        }else if(type.equalsIgnoreCase("WLAN")){
            wlanBarcodeResultTxt.setText(s);
        }
    }
}
