package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class Last2Activity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last2);
        tv = findViewById(R.id.activity_tv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_sl);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Last2Activity.this,"刷新完成",Toast.LENGTH_SHORT).show();
                        tv.setText("这是刷新之后的那句话");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }
}