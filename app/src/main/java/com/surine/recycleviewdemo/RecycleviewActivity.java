package com.surine.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleviewActivity extends AppCompatActivity {

    private List<String> mStrings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        //加载数据
        for(int i = 0;i<=20;i++){
            mStrings.add("我是"+i);
        }
        //激动人心的时候到了，马上就可以看到效果了。
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_main);
        //list型
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //gird型
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MainAdapter(this,mStrings));

    }
}
