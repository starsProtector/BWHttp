package com.sn.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果我要类型扩展的思路:
 * 1.创建一个新的布局
 * 2.新建一个类型的Type
 * 3.initData();里重新去做一个判断,数据添加类型时,添加一个fourType属性
 * 4.在适配器中把getViewTypeCount()里的数据添加一条即可
 * 5.再创建一个ViewHolder
 * 6.在getView方法里,在复制switch里的一个case,全部在增加一个即可
 */
public class MainActivity extends AppCompatActivity {

    //设置三种类型,对应我三种不同类型的Item,数字随意生成,主要是为了区分
    private static final int FristType = 0;
    private static final int TwoType = 1;
    private static final int ThreeType = 2;


    String[] texts = {"玉皇", "王母", "长蛾", "八戒", "如来", "易宸锋", "守星者", "部长", "大师", "收藏家"};

    private int[] images = {R.drawable.jx_left_listitem_1, R.drawable.jx_left_listitem_5,
            R.drawable.jx_left_listitem_2, R.drawable.jx_left_listitem_3,
            R.drawable.jx_left_listitem_4, R.drawable.jx_left_listitem_5,
            R.drawable.jx_left_listitem_6, R.drawable.jx_left_listitem_6,
            R.drawable.jx_left_listitem_4, R.drawable.jx_left_listitem_5};

    private List<DataBean> list;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initData();

        //查找控件
        mListView = (ListView) findViewById(R.id.lv);

        MyApdapter myApdapter = new MyApdapter();
        //设置适配器
        mListView.setAdapter(myApdapter);


    }

    /**
     * ListVIew多条目的Adapter,他比我们普通写的Adapter多了两个方法
     */
    private class MyApdapter extends BaseAdapter {
        class ViewHolder1 {
            TextView tv1;
        }

        class ViewHolder2 {
            TextView tv2;
            ImageView iv2;
        }

        class ViewHolder3 {
            TextView tv3;
            ImageView iv3;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        /*      1.创建VIewholder
                2.复用convertView参数
                3.查找控件
                4.给控件设置数据*/
        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder1 holder1 = null;
            ViewHolder2 holder2 = null;
            ViewHolder3 holder3 = null;
            //拿到ListVIew当前Item所对应DataBean数据的Type数据
            int type = getItemViewType(position);
            //存
            if (convertView == null) {
                //更加类型创建ViewHold
                switch (type) {
                    case FristType:
                        holder1 = new ViewHolder1();
                        convertView = View.inflate(MainActivity.this, R.layout.first_item, null);
                        holder1.tv1 = (TextView) convertView.findViewById(R.id.first_tv);
                        convertView.setTag(holder1);
                        break;
                    case TwoType:
                        holder2 = new ViewHolder2();
                        convertView = View.inflate(MainActivity.this, R.layout.two_item, null);
                        holder2.tv2 = (TextView) convertView.findViewById(R.id.two_tv);
                        holder2.iv2 = (ImageView) convertView.findViewById(R.id.two_ivv);
                        convertView.setTag(holder2);
                        break;
                    case ThreeType:
                        holder3 = new ViewHolder3();
                        convertView = View.inflate(MainActivity.this, R.layout.three_item, null);
                        holder3.tv3 = (TextView) convertView.findViewById(R.id.three_tv);
                        holder3.iv3 = (ImageView) convertView.findViewById(R.id.three_iv);
                        convertView.setTag(holder3);
                        break;
                }//取
            } else {
                switch (type) {
                    case FristType:
                        holder1 = (ViewHolder1) convertView.getTag();
                        break;
                    case TwoType:
                        holder2 = (ViewHolder2) convertView.getTag();
                        break;
                    case ThreeType:
                        holder3 = (ViewHolder3) convertView.getTag();
                        break;
                }
            }
            //设置数据类型
            switch (type) {
                case FristType:
                    DataBean dataBean = list.get(position);
                    String text = dataBean.getText();
                    holder1.tv1.setText(text);
                    break;
                case TwoType:
                    holder2.tv2.setText(list.get(position).getText());
                    holder2.iv2.setImageResource(list.get(position).getImages());
                    break;
                case ThreeType:
                    holder3.tv3.setText(list.get(position).getText());
                    holder3.iv3.setImageResource(list.get(position).getImages());
                    break;
            }

            return convertView;
        }

        //返回ListView所加载Item的类型
        @Override
        public int getItemViewType(int position) {
/*            DataBean dataBean = list.get(position);
            int type = dataBean.getType();
            return type;*/

            //得到对应的dataBean里的类型数据
            return list.get(position).getType();
        }

        //你的ListVIew有几种类型的Item,我们demo里有3中不同的类型
        @Override
        public int getViewTypeCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }


    /**
     * 这里是因为没有多条目的类型,手动我分一下,分为三种类型
     */
    private void initData() {
        list = new ArrayList<DataBean>();

        for (int x = 0; x < 10; x++) {
            DataBean data = new DataBean();
            //第一种类型的数据,也就是2等等,去展示一个类型
            if (x % 2 == 0) {
                data.setType(FristType);
                data.setText(texts[x]);
                System.out.println("位置" + x + "里面的数据" + data.toString());
            }//第二种类型的数据
            else if (x % 3 == 0) {
                data.setType(TwoType);
                data.setText(texts[x]);
                data.setImages(images[x]);
                System.out.println("位置" + x + "里面的数据" + data.toString());
            }//第三种类型的数据
            else {
                data.setType(ThreeType);
                data.setText(texts[x]);
                data.setImages(images[x]);
                System.out.println("位置" + x + "里面的数据" + data.toString());
            }
            list.add(data);
        }

    }
}
