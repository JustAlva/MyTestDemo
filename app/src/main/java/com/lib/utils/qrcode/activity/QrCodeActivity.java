package com.lib.utils.qrcode.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lib.utils.R;

public class QrCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);



        initView();
    }
    Button btnScan;
    private void initView() {

        //全屏，只扫二维码，使用QRCodeReaderView 框架
        Button btnJustQrCode = findViewById(R.id.btn_qrcode_scan_just_qrcode);
        btnJustQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //saomiao
          btnScan = findViewById(R.id.btn_qrcode_scan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator =  new IntentIntegrator(QrCodeActivity.this) ;
                integrator.setOrientationLocked(false)//设置扫码的方向
                        .setPrompt("将条码放置于框内")//设置下方提示文字
                        .setCameraId(0)//前置或后置摄像头
                        .setBeepEnabled(true)//扫码提示音，默认开启
                        .setOrientationLocked(false)//解锁屏幕方向锁定
                        .setCaptureActivity(ScanActivity.class)//设置扫码界面为自定义样式
                        .initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 49374:
                //IntentIntegrator.REQUEST_CODE=0x0000c0de ,16进制 c0de = 10进制 49374
                //处理扫描结果（在界面上显示）
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if(result != null) {
                    if(result.getContents() != null) {
                        btnScan.setText(result.getContents());
                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                }
                break;
        }
    }
}
