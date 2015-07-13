package com.android.tonight8.Audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

/**
 * 判断当前音频的输出源（耳机或外放）
 * Created by Administrator on 2015/7/10 0010.
 */
public class AudioOutputChangeReceiver extends BroadcastReceiver {
    private boolean isRegister;
    private Context context;
    private AudioManager manager;
    /**上个音频输出的源*/
    private int state;
    private final int HEAD_SET = 1;
    private final int SPEAKER = 2;
    private final int OTHER = 3;
    public AudioOutputChangeReceiver(Context context) {
        isRegister = false;
        this.context = context;
        state = -1;
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("已经接收改变");
        if(manager.isBluetoothA2dpOn() || manager.isWiredHeadsetOn()){//当前设备为蓝牙耳机
            System.out.println("耳机");
            if(state == SPEAKER || state == OTHER){
                //由外放变为耳机时，音量为最大值的二分之一
                if(manager.getStreamVolume(AudioManager.STREAM_MUSIC) > (manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) / 2)) {
                    manager.setStreamVolume(AudioManager.STREAM_MUSIC, (manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) / 2),AudioManager.FLAG_PLAY_SOUND);
                }
            }
            state = HEAD_SET;
        }else if(manager.isSpeakerphoneOn()){
            if(state == HEAD_SET){//耳机变为外放时，声音归0
                System.out.println("外放");
                manager.setStreamVolume(AudioManager.STREAM_MUSIC, 0,AudioManager.FLAG_PLAY_SOUND);
            }
            state = SPEAKER;
        } else {
            if(state == HEAD_SET){//耳机变为外放时，声音归0
                System.out.println("其他");
                manager.setStreamVolume(AudioManager.STREAM_MUSIC, 0,AudioManager.FLAG_PLAY_SOUND);
            }
            state = OTHER;
        }

    }

    public void doRegister() {
        if (!isRegister) {
            IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
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

}
