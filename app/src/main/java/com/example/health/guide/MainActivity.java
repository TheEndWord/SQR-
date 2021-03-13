package com.example.health.guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.health.HomeMenuActivity;
import com.example.health.R;

public class MainActivity<mProgressBar> extends AppCompatActivity {
    TextView tv;
    ProgressBar pb;
    int time = 5;
    SharedPreferences preferences;//存储键值对数据
    private SharedPreferences.Editor editor;
    Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                time--;
                if (time == 0) {
                    // 跳转界面
                    Intent intent = new Intent();
                    boolean isfirst = preferences.getBoolean("isfirst",true);
                    if (isfirst) {
                        intent.setClass(MainActivity.this, GuideActivity.class);
                        editor.putBoolean("isfirst",false);//写入不是第一次进入的进入
                        editor.commit();//提交本次修改进入
                    }else{
                        intent.setClass(MainActivity.this, HomeMenuActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }else {
                    tv.setText(time+"");
                    pb.setProgress(Integer.parseInt(5-time+""));
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.main_tv);
        pb = findViewById(R.id.main_pb);
        preferences = getSharedPreferences("health_pref",MODE_PRIVATE);
        editor = preferences.edit();//写入数据的对象
        handler.sendEmptyMessageDelayed(1,1000);

    }
}