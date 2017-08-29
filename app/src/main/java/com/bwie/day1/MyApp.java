package com.bwie.day1;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by DELL on 2017/8/29.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initUtils();
        initImageloder();
    }

    private void initImageloder() {


        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();


        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs()
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(configuration);

    }

    private void initUtils() {
        x.Ext.init(this);
    }
}
