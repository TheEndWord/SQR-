package com.example.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager aboutVp;
    TextView shareTv;
    LinearLayout pointLayout;
    List<View>viewList;//ViewPaper的数据源
    int[]picIds = {R.mipmap.ab1,R.mipmap.ab2,R.mipmap.ab3,R.mipmap.ab4,R.mipmap.ab5};
    private AboutAdapter adapter;
    List<ImageView>pointLish;//存放显示器小点点的集合
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                //接收到消息之后，需要使ViewPager页面向下滑动一页
                 int currentItem = aboutVp.getCurrentItem();
                aboutVp.setCurrentItem(currentItem+1);
                handler.sendEmptyMessageDelayed(1,5000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutVp = findViewById(R.id.about_vp);
        shareTv = findViewById(R.id.about_tv_share);
        pointLayout = findViewById(R.id.about_layout_point);
        shareTv.setOnClickListener(this);
        viewList = new ArrayList<>();
        pointLish = new ArrayList<>();
        // 初始化ViewPaper的页面信息
        for(int i = 0;i < picIds.length;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.item_aboutvp,null);
            ImageView iv = view.findViewById(R.id.item_aboutvp_iv);
            iv.setImageResource(picIds[i]);
            viewList.add(view);
            //创建指示器内容
            ImageView pointIv = new ImageView(this);
            //在代码中设置控件的宽高和外边距等属性
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,20,0);
            //将布局参数设置给ImageView
            pointIv.setLayoutParams(lp);
            pointIv.setImageResource(R.mipmap.a2);
            pointLish.add(pointIv);//添加到集合之中方便管理
            pointLayout.addView(pointIv);//添加到布局当中显示出来
        }
        pointLish.get(0).setImageResource(R.mipmap.a3);//设置第一个小圆点为选中状态
        //创建适配器对象
        adapter = new AboutAdapter(viewList);
        //设置适配器
        aboutVp.setAdapter(adapter);
        //发送切换页面消息
        handler.sendEmptyMessageDelayed(1,5000);
        //设置ViewPager页面监听器
        setVPListener();
    }

    private void setVPListener() {
        /*设置ViewPaper的监听器*/
        aboutVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0;i<pointLish.size();i++){
                    pointLish.get(i).setImageResource(R.mipmap.a2);
                }
                pointLish.get(position%pointLish.size()).setImageResource(R.mipmap.a3);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        //调用系统自带的分享功能
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String msg = "健康饮食非常的重要，了解饮食各种营养素和热量摄入，正确的食物让你变得更健康想要了解更多吗？快来下载健康饮食APP吧~~";
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        startActivity(Intent.createChooser(intent,"健康饮食分享"));
    }
}