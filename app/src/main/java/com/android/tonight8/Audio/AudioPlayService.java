package com.android.tonight8.Audio;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.FileDescriptor;
import java.io.IOException;

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
