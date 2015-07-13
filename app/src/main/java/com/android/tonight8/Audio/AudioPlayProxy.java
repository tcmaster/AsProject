package com.android.tonight8.Audio;

import android.os.IBinder;

import java.io.FileDescriptor;

/**
 * 播放服务代理类
 * Created by LiXiaoSong on 2015/7/13 0013.
 */
public class AudioPlayProxy {
    private MyAudioBinder binder;

    private AudioPlayProxy(IBinder binder) {
        this.binder = (MyAudioBinder) binder;
    }

    /**
     * binder类型必须为MyAudioBinder
     *
     * @param binder
     * @return
     */
    public static AudioPlayProxy asBinder(IBinder binder) {
        AudioPlayProxy proxy = new AudioPlayProxy(binder);
        return proxy;
    }

    /**
     * 当前的代理是否有效
     */
    public boolean canUse() {
        if (binder != null && binder.isBinderAlive())
            return true;
        else return false;
    }

    /**
     * 设置音频源
     *
     * @param httpUrl
     */
    public void initSounds(String httpUrl) {
        if (binder != null)
            binder.initSounds(httpUrl, true);
    }

    /**
     * 设置本地音频源
     *
     * @param fd
     */
    public void initSounds(FileDescriptor fd) {
        if (binder != null)
            binder.initSounds(fd);
    }

    /**
     * 播放声音
     *
     * @param isLoop
     */
    public void playSound(boolean isLoop) {
        if (binder != null)
            binder.play(isLoop);
    }

    /**
     * 停止播放声音
     */
    public void stopSound() {
        if (binder != null)
            binder.stop();
    }

    /**
     * 暂停播放声音
     */
    public void pauseSound() {
        if (binder != null)
            binder.pause();
    }

    /**
     * 跳转到某个界面
     *
     * @param pos
     */
    public void seekTo(int pos) {
        if (binder != null)
            binder.seekTo(pos);
    }

    /**
     * 释放音频源
     */
    public void releaseSound() {
        if (binder != null)
            binder.releaseSounds();
    }
}
