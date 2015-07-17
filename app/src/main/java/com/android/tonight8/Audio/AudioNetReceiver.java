package com.android.tonight8.Audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络状态监听
 * Created by LiXiaoSong on 2015/7/10 0010.
 * 当项目中有设置的选项时，需要添加相关设置项，来决定是否使用2g信号等内容
 */
public class AudioNetReceiver extends BroadcastReceiver {
    private boolean isRegister;
    private Context context;
    private ConnectivityManager manager;
    private NetChangeCallBack callBack;

    public AudioNetReceiver(Context context, NetChangeCallBack callback) {
        isRegister = false;
        this.context = context;
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.callBack = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo netInfo = manager.getActiveNetworkInfo();//得到当前首选可用网络
        if (netInfo != null && netInfo.isConnected())
            callBack.onNetConnection(netInfo.getType());
         else
            callBack.onNetUnconnection();

    }

    public void doRegister() {
        if (!isRegister) {
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            context.registerReceiver(this, filter);
            isRegister = true;
        }
    }

    public void doUnregister() {
        if (isRegister) {
            context.unregisterReceiver(this);
            isRegister = false;
        }
    }

    /**
     * 网络连接接口
     */
    public interface NetChangeCallBack {
        /**
         * 当前有可用且连接正常的网络
         *
         * @param type 可连接的类型（手机，wifi,蓝牙等）
         *               以下类型的其中一种TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET, TYPE_BLUETOOTH,（这些类型定义在 ConnectivityManager中）
         */
        void onNetConnection(int type);

        /**
         * 当前没有可用的网络
         */
        void onNetUnconnection();
    }
}
