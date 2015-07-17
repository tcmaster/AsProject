package com.android.tonight8.Audio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同一时刻仅能播放一个音频的服务
 */
public class AudioPlayService extends Service {
    private MyAudioBinder binder;
    public AudioPlayService() {
        binder = new MyAudioBinder(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        binder.bind();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        binder.unbind();
        return super.onUnbind(intent);
    }

}
