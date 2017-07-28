package com.sn.asynctaskdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 开源框架:就是工具类的升级版,java封装,多态,对象,的最高体现.
 * android开发由一切都是自己写过度框架时代.很多复杂的业务逻辑,现在变成了只需要依赖开源框架,然后几行代码就能完成
 *
 *
 * AsyncTask:(实际就和handler的作用差不多)
 * 加载一张图片然后包含一个进度条
    1.新建一个类,extends AsyncTask类<Params, Progress, Result>{}//三个泛型可用大写的Void替代(Void是小写void的对象类型)
    2.复写其抽象方法,在异步任务(也就是doInBackground方法)执行之前,执行该方法,在主线程执行protected void onPreExecute() {super.onPreExecute();}
    3.复写抽象方法,(也就是doInBackground方法)该方法在后台执行(可以理解为子线程),可以做耗时的工作protected Result doInBackground(Params... params){return null;}可以接收到doInBackground返回数据
    4.复写其方法抽象方法,在异步任务(也就是doInBackground方法)执行之后,执行该方法,在主线程执行protected void onPostExecute(Void result) {super.onPostExecute(result);}
    5.开启一个异步任务,在主线程里新建继承了AsyncTask自定义的类,调用execute()方法.
 new myAsyncTasK().execute();//execute()方法一定要用,相当于开启线程的start,调了1个小时的bug.
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化的代码
        initView();
    }

    public void showPic(View view){
        //创建出了一个AsyncTask对象,必须要覆写doInBackground,以下onPreExecute,doInBackground,onPostExecute是有执行顺序的
        /**
         * 1.类中的三个泛型:
         params:第一个泛型,就是5.execute方法的参数类型(其实就是他传的实际参数),也是3.doInBackground方法的参数类型
         提示:3.doInBackground(Params... params){内部使用时,params就是对应类型的数组,Prarms[0]就是第一个Params}

         Progress:第二个泛型,就是publishProgress(必须在3.doInBackground方法里调用)方法的参数类型,他负责发布进度,类似进度条,
         也是onProgressUpdate方法的参数类型,他得到的是来自publishProgress的实际参数,进度更新时调用,在主线程中执行,可更新UI
         提示:使用到第二个泛型,就要使用到publishProgress,并继续使用到onProgressUpdate(此方法要复写,具体格式如下:)
         protected void onProgressUpdate(Progress... values){super.onProgressUpdate(values);}//values就是对应类型的数组

         Result:第三个泛型,既是3.doInBackground的返回值类型,并且它还是OnPostExecute的参数类型;
         应用场景:在子线程(doInBackground)获取信息,在OnpostExcute里去显示;
         */
        AsyncTask<Void, Void, Bitmap> asyncTask = new AsyncTask<Void, Void, Bitmap>() {
            //1.在子线程开启之前执行此方法
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                String name = Thread.currentThread().getName();
                Log.d("sx","onPreExecute()运行线程是"+name);
                //展示ProgressBar,View.GONE 和 View.INVISIBLE:是否占用UI的空间位置.
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            //2.此方法在子线程中执行
            @Override
            protected Bitmap doInBackground(Void... voids) {
                String name = Thread.currentThread().getName();
                Log.d("sx","doInBackground()运行线程是"+name);

                try {
                    String path = "http://img.my.csdn.net/uploads/201407/26/1406383059_8814.jpg";
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);

                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200){
                        //获取输入流,此时就已经获取到服务器里的数据到我们的客户端里
                        InputStream inputStream = httpURLConnection.getInputStream();
                        //把字节流转化成Bitam
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
            //3.此方法运行在主线程
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                String name = Thread.currentThread().getName();
                Log.d("sx","onPostExecute运行线程是"+name);
                //加载bitmap图片
                imageView.setImageBitmap(bitmap);
                //隐藏进度条
                progressBar.setVisibility(View.GONE);

                super.onPostExecute(bitmap);
            }
        };
        asyncTask.execute();


    }


    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
}
