package com.android.barcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {

    TextView barcodeResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barcodeResultTxt = (TextView)findViewById(R.id.barcodeResult);
    }

    public void scanBarcodeOnClick(View v){
        Intent intent = new Intent(this,ScanBarcodeActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcodes = data.getParcelableExtra("barcode");
                    barcodeResultTxt.setText("Barcode value: "+ barcodes.displayValue);
                }else{
                    barcodeResultTxt.setText("No Barcode Found!");
                }
            }
        }else{

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
