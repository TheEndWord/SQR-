package com.example.health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class DraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dra);
        final DrawerLayout drawerLayout=findViewById(R.id.drawerLayout);
        //给按钮添加一个监听器
        findViewById(R.id.top_view_left_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开侧滑菜单
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.dra_btn:
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(DraActivity.this,HomeMenuActivity.class);
                break;
            case R.id.dra_btn2:
                intent.setClass(DraActivity.this,Last2Activity.class);
                break;
            case R.id.dra_btn3:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.familydoctor.com.cn/yinshi/yyzd/yydp/"));
                break;
        }
        startActivity(intent);
    }
}
