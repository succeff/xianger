package com.baway.xianger;

import android.app.Application;

/**
 * 类的描述：
 * 时间：  2017/8/24.21:10
 * 姓名：chenlong
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
