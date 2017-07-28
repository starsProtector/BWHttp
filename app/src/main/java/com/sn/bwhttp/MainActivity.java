package com.sn.bwhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bawei.xlistviewlibrary.utils.UrlConnection;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    //网址
    String urlPath ="http://huixinguiyu.cn/Assets/js/data.js";
    String PostPath ="http://169.254.53.96:8080/web/LoginServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.hh);


    }

    public void HttpCon(View view){

        //网络判断
/*
        boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(this);
        if (netWorkAvailable){
            Toast.makeText(MainActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
        }
*/

        new Thread(){
            public void run() {

                //get请求
//                String urlConnect = UrlConnection.getUrlConnect(urlPath);

                //Post请求
                HashMap<String , Object> map = new HashMap<>();
                map.put("qq","10000");
                map.put("pwd","abcde");
                String s = UrlConnection.postUrlConnect(PostPath, map);
                System.out.println(s);
            };
        }.start();

    }


}
