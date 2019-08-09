package com.skl.netease_arouter;

import android.app.Application;

import com.skl.arouter.ARouter;


/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }

}
