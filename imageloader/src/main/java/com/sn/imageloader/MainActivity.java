package com.sn.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 1.添加权限
 * 2.添加依赖,搭建环境
 * 3.在自定义的Application中初始化ImageLoader,配置清单文件
 * 4.基本使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        String path = "http://t2.27270.com/uploads/tu/201610/198/uxkqdrirglw.jpg";
        //拿到ImageLoader的对象
        ImageLoader imageLoader = ImageLoader.getInstance();

        //A.ImageLoader的基本使用,环境配置好,一行代码加载网络图片   参数1.URL图片网址,String类型   2.控件对象
        ImageLoader.getInstance().displayImage(path, imageView);

/*        APP app = new APP();
        DisplayImageOptions options = app.getOption();*/

        //B.ImageLoader加载网络图片(带参数设置)
//        imageLoader.displayImage(path,imageView,options);

        //C.ImageLoader加载网络图片(带参数设置),自带监听回调
       /* imageLoader.displayImage(path, imageView, options, new ImageLoadingListener() {
            //加载图片开始回调
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }
            //加载图片失败回调
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }
            //加载图片成功的时候回调
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }
            //加载图片取消的时候回调
            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });*/

        //D.ImageLoader加载网络图片(带参数设置),自带监听回调,同时可以显示加载进度的回调
       /* imageLoader.displayImage(path, imageView, options, new ImageLoadingListener() {
            //加载图片开始回调
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            //加载图片失败回调
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            //加载图片成功的时候回调
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            //加载图片取消的时候回调
            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        }, //加载图片的进度条的回调
                new ImageLoadingProgressListener() {
                    //参数1.网址    2.ImageView控件对象
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
                System.out.println("控件"+view);
            }
        });*/

    }


}
