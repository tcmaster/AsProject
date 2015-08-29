package com.android.tonight8.Audio;


import android.media.MediaPlayer;

/**
 * Created by Administrator on 2015/7/9 0009.
 */
public class AudioErrorListener  implements MediaPlayer.OnErrorListener{
    private AudioErrorInterface callback;
    public AudioErrorListener(AudioErrorInterface callback){
        this.callback = callback;
    }
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        switch (extra){
            case MediaPlayer.MEDIA_ERROR_IO://网络等原因，音频出现问题
                int pos = mp.getCurrentPosition();//记录当前播放的时间点
                System.out.println("MyMediaPlayer source or new error");
                if(callback != null) callback.onNetError(pos);
                break;
            case MediaPlayer.MEDIA_ERROR_TIMED_OUT://操作时间过长
                System.out.println("operator time is too long");
                break;
        }

        return true;
    }
    public interface AudioErrorInterface{
        void onNetError(int currentPos);
    }
}
