package com.android.tonight8.Audio;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by Administrator on 2015/7/9 0009.
 */
public class AudioFocusHelper implements AudioManager.OnAudioFocusChangeListener {
    private Context mContext;
    private AudioManager mAudioManager;
    private FocusChangeCallBack callBack;
    public AudioFocusHelper(Context context,FocusChangeCallBack callBack){
        this.mContext = context;
        this.callBack = callBack;
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }
    public boolean requestFocus(){
        //请求获得一个较长的持续的音频焦点
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);
    }
    public boolean abandonFocus(){
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                mAudioManager.abandonAudioFocus(this);
    }
    @Override
    public void onAudioFocusChange(int focusChange) {
        switch(focusChange){
            case AudioManager.AUDIOFOCUS_GAIN:
                callBack.onGain();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                callBack.onLoss();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                callBack.onLossTransient();
                break;
        }
    }

    /**
     * 焦点变化的回调
     */
    public interface FocusChangeCallBack{
        /**焦点暂时丢失*/
        public void onLossTransient();
        /**焦点丢失*/
        public void onLoss();
        /**获得焦点*/
        public void onGain();
    }
}
