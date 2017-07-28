package com.sn.imageloader;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * date:2017/7/17
 * author:易宸锋(dell)
 * function:
 * 自定义APpLication的步骤    1.自定义一个类,继承Application   2.清单文注册自定义的Application
 * applciation:先于androidAPP的组件存在,时对这个应用的信息配置.
 * 做任何第三方框架等等初始化,都要在Application中进行
 */

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化ImageLoader设置
        initImageLoader();
    }

    private void initImageLoader() {
        //设置ImageLoader的配置信息
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(APP.this)
                .memoryCacheExtraOptions(480, 800) //缓存图片最大的长和宽
                .threadPriority(2) //线程池的数量
                .memoryCacheSizePercentage(4) // default
                .memoryCacheSize(2*1024*1024)//设置内存缓存区的大小
                .diskCacheSize(50 * 1024 * 1024)//设置SD卡缓存区大小
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()//打开日志neirong
                .build();
        //通过单例的模式,拿到对象,设置我们自定义配置的ImageLoader的配置信息
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions getOption(){
        //创建对象,又叫链式调用法.
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_loading) //设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_empty) //设置图片url为null的时候显示的图片
                .showImageOnFail(R.drawable.ic_error) // 设置图片uri有错误,解密的过程中发生错误显示的图片
                .delayBeforeLoading(1000)
                .cacheInMemory(true) //设置下载的图片是否缓存到内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存到SD卡中
                .build();
        return options;
    }


}
