package com.bwie.day1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.body.StringBody;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import bean.News;
import bean.News2;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {


    private String url="http://v.juhe.cn/toutiao/index?key=22a108244dbb8d1f49967cd74a0c144d";
    private List<News2> list;
    private MyAdapter adapter;
    @ViewInject(R.id.lv) ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        x.view().inject(this);

        initData();
    }

    private void initData() {
        lv = (ListView) findViewById(R.id.lv);

        list = new ArrayList<>();
        adapter = new MyAdapter(this,list);
        lv.setAdapter(adapter);
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                System.out.println("================"+result);
                //解析字符串
                parseData(result);
                sedData();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });

    }

    private void sedData() {
        if(adapter==null)
        {
            adapter=new MyAdapter(this,list);
            lv.setAdapter(adapter);
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
    }

    private void parseData(String result) {

        Gson gson=new Gson();
        News news = gson.fromJson(result, News.class);
        List<News.ResultBean.DataBean> data = news.result.data;

        for (int i = 0; i < data.size(); i++) {
            News.ResultBean.DataBean dataBean = data.get(i);
            String title = dataBean.title;
            String thumbnail_pic_s = dataBean.thumbnail_pic_s;
            News2 news2=new News2(title,thumbnail_pic_s);
            list.add(news2);
        }
    }
}
