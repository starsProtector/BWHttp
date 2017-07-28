package com.bawei.network.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bawei.network.R;
import com.bawei.network.utils.NetWorkUtils;

/**
 *  掌握网络是否连接以及网络类型的判断,掌握无网络情况下，跳转设置网络设置界面
 *  1.编写网络判断的工具类
 *  2.使用网络判断的工具类,判断当前用户手机的网络情况
 *  3.自定义广播类
 *  4.完成注册
 *  5.无网络时，跳转网络设置界面
 *  提示:添加权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //使用自己编写的工具类,判断网络是否连接
        boolean available = NetWorkUtils.isNetWorkAvailable(this);
        if (available) {
            Toast.makeText(MainActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();

        }

        //使用自己编写的工具类,判断是否是wifi
        boolean wifi = NetWorkUtils.isWifi(this);
        if (wifi) {
            Toast.makeText(MainActivity.this, "wifi网络连接成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "wifi网络连接失败", Toast.LENGTH_SHORT).show();

        }

/*
        //注册广播
        MyRecever recever = new MyRecever();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.bawei.net");
        registerReceiver(recever, filter);

        //使用自己编写的工具类,判断是否是手机流量
        boolean mobile = NetWorkUtils.isMobile(this);
        //有网做对应的操作
        if (mobile) {
            Toast.makeText(MainActivity.this, "手机流量网络连接成功", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "手机流量网络连接失败", Toast.LENGTH_SHORT).show();
            //无网络时，跳转网络设置界面
            Intent intent = new Intent("com.bawei.net");
            intent.putExtra("net", "亲，断网了，应该去设置网络了");
            sendBroadcast(intent);
            Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
            startActivity(wifiSettingsIntent);
        }
        */
    }


/*    private class MyRecever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("com.bawei.net")){
                String net = intent.getStringExtra("net");
                Toast.makeText(MainActivity.this, "接收广播成功:"+net, Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
