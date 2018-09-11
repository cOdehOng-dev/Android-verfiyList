package com.example.c0de_h0ng.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText imei;

    Button VA0btn;
    Button VA4btn;
    Button VB0btn;
    Button VC0btn;

    Button getVA0btn;
    Button getVA4btn;
    Button getVB0btn;
    Button getVC0btn;

    TextView VA0;
    TextView VA4;
    TextView VB0;
    TextView VC0;

    TextView Get;

    String DUI;

    Random ru = new Random(10000);

    String hA0 = SHA256.sha256("ewqreqwlhkaadf");
    String hA4 = SHA256.sha256("klasdfjklsfoad");
    String hB0 = SHA256.sha256("dfajweoprrewda");

    List verifyList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imei = (EditText)findViewById(R.id.imei);

        VA0btn = (Button)findViewById(R.id.va0btn);
        VA4btn = (Button)findViewById(R.id.va4btn);
        VB0btn = (Button)findViewById(R.id.vb0btn);
        VC0btn = (Button)findViewById(R.id.vc0btn);

        getVA0btn = (Button)findViewById(R.id.getva0btn);
        getVA4btn = (Button)findViewById(R.id.getva4btn);
        getVB0btn = (Button)findViewById(R.id.getvb0btn);
        getVC0btn = (Button)findViewById(R.id.getvc0btn);

        VA0 = (TextView)findViewById(R.id.VA0);
        VA4 = (TextView)findViewById(R.id.VA4);
        VB0 = (TextView)findViewById(R.id.VB0);
        VC0 = (TextView)findViewById(R.id.VC0);

        Get = (TextView)findViewById(R.id.get);

        DUI = imei.getText().toString();
        final String hDUI = SHA256.sha256(DUI);

        // 난수 생성
        final String ru2 = SHA256.sha256(String.valueOf(ru));
        final String ru3 = SHA256.sha256(String.valueOf(ru2));
        final String ru4 = SHA256.sha256(String.valueOf(ru3));
        final String ru5 = SHA256.sha256(String.valueOf(ru4));

        VA0btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eVA0 = SHA256.sha256(hDUI + ru);
                System.out.println("ru2 >>> " + ru2);
                verifyList.add(eVA0);
                VA0.setText(eVA0);
            }
        });

        VA4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // h(𝑟_𝑢2||h(DUI))
                String h2 = SHA256.sha256(hDUI + ru2);
                // h(h(𝑟_𝑢2||h(DUI)) || h(A0))
                String eVA4 = SHA256.sha256(h2 + hA0);
                System.out.println("ru3 >>> " + ru3);
                verifyList.add(eVA4);
                VA4.setText(eVA4);
            }
        });

        VB0btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // h(𝑟_𝑢3||h(DUI))
                String h3 = SHA256.sha256(hDUI + ru3);
                // h(h(𝑟_𝑢3||h(DUI))||h(A4))
                String eVB0 = SHA256.sha256(h3 + hA4);
                System.out.println("ru4 >>> " + ru4);
                verifyList.add(eVB0);
                VB0.setText(eVB0);
            }
        });

        VC0btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // h(𝑟_𝑢4||h(DUI))
                String h4 = SHA256.sha256(hDUI + ru4);
                // h(h(𝑟_𝑢4||h(DUI))||h(B0))
                String eVC0 = SHA256.sha256(h4 + hB0);
                System.out.println("ru5 >>> " + ru5);
                verifyList.add(eVC0);
                VC0.setText(eVC0);
            }
        });

        getVA0btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get.setText(verifyList.get(0).toString());
            }
        });

        getVA4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get.setText(verifyList.get(1).toString());
            }
        });

        getVB0btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get.setText(verifyList.get(2).toString());
            }
        });

        getVC0btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get.setText(verifyList.get(3).toString());
            }
        });

    }
}
