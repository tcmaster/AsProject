package com.android.tonight8.ui.view.somonthemotionkeyboard.emotion;/**
 * Created by cpoopc on 2015/9/8.
 */


import com.android.tonight8.ui.view.somonthemotionkeyboard.emotion.bean.EmotionEntity;
import com.android.tonight8.ui.view.somonthemotionkeyboard.utils.DebugLog;

/**
 * User: cpoopc
 * Date: 2015-09-08
 * Time: 16:08
 * Ver.: 0.1
 */
public enum EmotionInputEventBus {
    instance;
    private long lastDealTime = 0, lastEndTime = 0;

    public interface EmotionInputEventListener {
        void onEmotionInput(EmotionEntity emotionEntity);
    }

    private EmotionInputEventListener emotionInputEventListener;

    public void setEmotionInputEventListener(EmotionInputEventListener emotionInputEventListener) {
        this.emotionInputEventListener = emotionInputEventListener;
    }

    public void postEmotion(EmotionEntity emotionEntity) {
        DebugLog.i("lastDealTime:" + lastDealTime);
        if (emotionInputEventListener != null) {
            long startTime = System.currentTimeMillis();
            if (startTime < lastEndTime + 100) {
                DebugLog.e(startTime + ", " + (lastEndTime + 100));
                return;
            }
            emotionInputEventListener.onEmotionInput(emotionEntity);
            lastEndTime = System.currentTimeMillis();
            lastDealTime = lastEndTime - startTime;
        }
    }

}
