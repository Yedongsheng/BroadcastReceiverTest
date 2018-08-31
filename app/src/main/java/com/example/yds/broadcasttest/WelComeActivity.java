package com.example.yds.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;

public class WelComeActivity extends Activity {
    Button btn;
    private MyReceiver myReceiver;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        myReceiver = new MyReceiver();
        btn = findViewById(R.id.btn);
        IntentFilter filter = new IntentFilter();
        filter.addAction("ceshi");
        registerReceiver(myReceiver,filter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    Thread.sleep(3000);
                    intent.setAction("finish");
                    intent.putExtra("text", "welcome页面传来的值");
                    sendBroadcast(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            btn.setVisibility(View.VISIBLE);
//
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
