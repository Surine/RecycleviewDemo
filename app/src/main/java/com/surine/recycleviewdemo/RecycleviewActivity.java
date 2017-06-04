package com.surine.recycleviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class RecycleviewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> mStrings = new ArrayList<>();
    MainAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        //加载数据
        for(int i = 0;i<=20;i++){
            mStrings.add("我是"+i);
        }
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        //激动人心的时候到了，马上就可以看到效果了。
        recyclerView = (RecyclerView) findViewById(R.id.rec_main);
        adapter = new MainAdapter(this,mStrings);
        //list型
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(RecycleviewActivity.this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //gird型
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        adapter.addData(1);
                        //显示或隐藏刷新进度条
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
              }
            });


        recyclerView.addOnScrollListener(new OnScrollListener() {
            //标记当前是否时向最后一项滑动
            boolean isLast = false;
            //滚动状态改变时
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取到当前的layoutmanager
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(newState == SCROLL_STATE_IDLE){
                    //得到管理器里面的最后一个item
                    int Last = layoutManager.findLastVisibleItemPosition();
                    //得到管理器全部的item
                    int total = layoutManager.getItemCount();
                    //判断当前是不是滑动，是否到了最下面的item地方，如果全是就加载
                    if(Last == total-1&&isLast){
                        //开始上拉加载
                        adapter.addData(total);
                    }
                }
            }
            //滚动结束后
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果dy大于0，则说明在向下滑
                if(dy>0){
                    isLast = true;
                }else{
                    isLast  =false;
                }
            }
        });
      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                //添加
                adapter.addData(1);
                break;
            case R.id.remove:
                //删除
                adapter.removeData(1);
                break;
        }

        return true;
    }
}
