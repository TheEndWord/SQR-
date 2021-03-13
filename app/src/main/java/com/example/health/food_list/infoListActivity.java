package com.example.health.food_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health.R;
import com.example.health.bean.FoodBean;
import com.example.health.bean.FoodUtils;
import com.example.health.food_grid.FoodDescActivity;
import com.example.health.food_grid.FoodGridActivity;

import java.util.ArrayList;
import java.util.List;

public class infoListActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchEt;
    ImageView searchIv,flushIv;
    ListView showLv;
    // ListView 内部数据源
    List<FoodBean> mDatas;
    List<FoodBean> allFoodList;
    private InfoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        // 查找控件
        initView();
        // 找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allFoodList = FoodUtils.getAllFoodList();
        mDatas.addAll(allFoodList);
        // 创建适配器 Basedapter的子类
        adapter = new InfoListAdapter(this, mDatas);
        showLv.setAdapter(adapter);//设置适配器
        //设置单向点击监听功能
        setListener();
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodBean foodBean = mDatas.get(position);
                Intent intent = new Intent(infoListActivity.this, FoodDescActivity.class);
                intent.putExtra("food",foodBean);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        searchEt = findViewById(R.id.info_et_search);
        searchIv = findViewById(R.id.info_iv_search);
        flushIv = findViewById(R.id.info_iv_flush);
        showLv = findViewById(R.id.infolist_lv);
        searchIv.setOnClickListener(this);//添加点击事件的监听器
        flushIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_iv_flush:
                searchEt.setText("");
                mDatas.clear();
                mDatas.addAll(allFoodList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.info_iv_search:
                //获取输入内容判断不为空
                String msg = searchEt.getText().toString().trim();//获取输入的信息
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断所有食物列表的标题是否包含输入的内容 如果包含，就添加到小集合当中
                List<FoodBean>list = new ArrayList<>();
                for(int i = 0;i<allFoodList.size();i++){
                    String title = allFoodList.get(i).getTitle();
                    if (title.contains(msg)) {
                        list.add(allFoodList.get(i));
                    }
                }
                mDatas.clear();//清空ListView的适配器数据源内容
                mDatas.addAll(list);//添加新的数据到数据源当中
                adapter.notifyDataSetChanged();//提示适配器更新
                break;
        }
    }
}