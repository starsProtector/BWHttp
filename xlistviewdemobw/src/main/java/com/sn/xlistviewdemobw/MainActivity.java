package com.sn.xlistviewdemobw;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.bawei.xlistviewlibrary.XListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    private List<String> list;
    private ArrayAdapter<String> adapter;
    private XListView lv;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //刷新listView控件
            adapter.notifyDataSetChanged();
            close();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (XListView) findViewById(R.id.xlistView);
        //初始化数
        initData();

        //开启下拉刷新
        lv.setPullRefreshEnable(true);
        //开启加载更多可用
        lv.setPullLoadEnable(true);

        //创建listView的通用适配器        参数1.上下文    参数2.布局资源     参数3.控件ID   参数4.要给控件设置的数据
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, list);

        //设置适配器
        lv.setAdapter(adapter);
        //设置XlistView的上拉加载,下来刷新的接口
        lv.setXListViewListener(this);
    }


    private void initData() {
        //开发中,此处在子线程中请求网络,解析数据,封装bean类
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("给我个萌妹子!!!");
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        //该方法运行在主线程
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("走了下拉刷新的操作");
                //
                list.add(0, "给你一个高圆圆");
                //发送一个空的消息
                handler.sendEmptyMessage(0);
            }
        }, 2000);

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        //该方法运行在主线程
        System.out.println("走了加载更多的操作");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add("给你10个林志领");
                }
                handler.sendEmptyMessage(0);
            }
        }, 2000);
    }

    //关闭刷新,加载的业务逻辑
    protected void close() {
        //停止加载更多
        lv.stopLoadMore();
        //停止刷新
        lv.stopRefresh();
        lv.setRefreshTime("2017:07:16");
    }
}
