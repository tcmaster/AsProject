package com.android.tonight8.Audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * 音频服务的binder
 * Created by lixiaosong on 2015/7/9 0009.
 */
class MyAudioBinder extends Binder {
    private final int PAUSE = 1;
    private final int PLAYING = 2;
    private final int STOP = 3;
    private final int IDLE = 4;
    /**
     * 监听输出设备改变的广播
     */
    AudioOutputChangeReceiver outReceiver;
    private MediaPlayer mp;
    /**
     * 获取焦点
     */
    private AudioFocusHelper focusHelper;
    /**
     * 设置当前的播放状态
     */
    private int state;
    /**
     * 防止多次进行prepare
     */
    private boolean isPrepare;
    private Context service;
    /**
     * 当前发生过网络异常
     */
    private boolean hasNetError;
    /**
     * 当前音频的网络地址
     */
    private String httpUrl;
    /**
     * 监听网络状态改变的广播
     */
    private AudioNetReceiver receiver;
    /**
     * 记录出现网络问题时，当前音乐播放的位置
     */
    private int errorPos;

    public MyAudioBinder(Context context) {
        this.service = context;
        state = IDLE;
        isPrepare = false;
        errorPos = 0;
    }

    /**
     * 初始化binder
     */
    public void bind() {
        //初始化音频焦点帮助工具
        focusHelper = new AudioFocusHelper(service, new AudioFocusHelper.FocusChangeCallBack() {
            @Override
            public void onLossTransient() {
                pause();//暂停播放音乐
            }

            @Override
            public void onLoss() {
                //关闭各种音乐
            }

            @Override
            public void onGain() {
                play(false);//继续播放音乐
            }
        });
        //初始化声音播放工具
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setOnErrorListener(new AudioErrorListener(new AudioErrorListener.AudioErrorInterface() {
            @Override
            public void onNetError(int currentPos) {//网络异常
                isPrepare = false;
                hasNetError = true;
                errorPos = currentPos;
                releaseSounds();//释放音频源，取消准备状态
            }
        }));
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseSounds();
            }
        });
        if (receiver == null) {
            receiver = new AudioNetReceiver(service, new AudioNetReceiver.NetChangeCallBack() {
                @Override
                public void onNetConnection(int type) {
                    System.out.println("网络恢复");
                    if (hasNetError) {//曾经有过网络异常
                        hasNetError = false;
                        initSounds(httpUrl, false);//重新获得音频
                    }
                }

                @Override
                public void onNetUnconnection() {
                    System.out.println("网络出现异常");
                }
            });
        }
        if (outReceiver == null)
            outReceiver = new AudioOutputChangeReceiver(service);
        outReceiver.doRegister();
        receiver.doRegister();

    }

    /**
     * 解绑时，释放资源等操作
     */
    public void unbind() {
        if (focusHelper != null) {
            //释放声音播放工具
            state = IDLE;
            mp.release();
            mp = null;
        }
        outReceiver.doUnregister();
        receiver.doUnregister();

    }

    /**
     * 初始化音频（播放哪段音频）
     *
     * @param httpUrl
     * @param changeSound 是否为切换歌曲
     */
    public void initSounds(String httpUrl, boolean changeSound) {
        this.httpUrl = httpUrl;
        if (changeSound) errorPos = 0;
        if (mp != null) try {
            mp.setDataSource(this.httpUrl);
            state = IDLE;
        } catch (IOException e) {
            System.out.print("音频连接已失效");
        }
    }

    public void initSounds(FileDescriptor fd) {
        if (mp != null) try {
            mp.setDataSource(fd);
            state = IDLE;
        } catch (IOException e) {
            System.out.print("音频连接已失效");
        }
    }

    /**
     * 释放当前音频
     * 在释放以后，必须initSounds才能再次使用
     */
    public void releaseSounds() {
        if (mp != null) {
            stop();
            mp.reset();
            state = IDLE;
            focusHelper.abandonFocus();
        }
    }

    /**
     * 播放/重新播放当前音频
     *
     * @param isloop 是否循环
     */
    public void play(final boolean isloop) {
        if (mp != null) {
            if (focusHelper.requestFocus()) {//获取音频焦点
                if (!isPrepare && !hasNetError && (state == IDLE || state == STOP)) {

                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            isPrepare = false;
                            state = PLAYING;
                            if (errorPos > 0) {//从出现错误的地方开始播放
                                seekTo(errorPos);
                                errorPos = 0;
                            }
                            mp.setLooping(isloop);
                            mp.start();

                        }
                    });
                    isPrepare = true;//点击准备到准备完成前，不能再次进行prepare
                    mp.prepareAsync();
                } else if (state == PAUSE) {
                    mp.start();
                    state = PLAYING;
                }
            }
        }
    }

    /**
     * 停止播放当前音频
     */
    public void stop() {
        if (mp != null && (state == PLAYING || state == PAUSE)) {
            focusHelper.abandonFocus();
            mp.stop();
            state = STOP;
        }
    }

    /**
     * 暂停当前音频
     */
    public void pause() {
        if (mp != null && state == PLAYING) {
            mp.pause();
            state = PAUSE;
        }
    }

    /**
     * 跳转到某个位置
     *
     * @param msec
     */
    public void seekTo(int msec) {
        if (mp != null && state == PLAYING || state == PAUSE) {
            mp.seekTo(msec);
        }
    }

    public int getCurrentPosition() {
        if (mp != null)
            return mp.getCurrentPosition();
        else return 0;
    }
}
