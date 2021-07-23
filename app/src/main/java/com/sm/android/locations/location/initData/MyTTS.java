package com.sm.android.locations.location.initData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Locale;

public class MyTTS extends UtteranceProgressListener implements TextToSpeech.OnInitListener {
    private static final String TAG = MyTTS.class.getSimpleName();


    private static MyTTS mInstance;
 
    public static MyTTS getInstance() {
        if (mInstance == null) {
            synchronized (MyTTS.class) {
                if (mInstance == null) {
                    mInstance = new MyTTS();
                }
            }
        }
        return mInstance;
    }
 
    private MyTTS() {
        super();
    }
 
    private TextToSpeech mTextToSpeech;
 
    public void init(Context context) {
        Log.i("ylt111", "init: "+TAG);
        mTextToSpeech = new TextToSpeech(context, this);
        mTextToSpeech.setOnUtteranceProgressListener(this);
    }
    @SuppressLint("NewApi")
    public void speak(String text) {
        if (mTextToSpeech != null)
              /*
                TextToSpeech的speak方法有两个重载。
                // 执行朗读的方法
                speak(CharSequence text,int queueMode,Bundle params,String utteranceId);
                // 将朗读的的声音记录成音频文件
                synthesizeToFile(CharSequence text,Bundle params,File file,String utteranceId);
                第二个参数queueMode用于指定发音队列模式，两种模式选择
                （1）TextToSpeech.QUEUE_FLUSH：该模式下在有新任务时候会清除当前语音任务，执行新的语音任务
                （2）TextToSpeech.QUEUE_ADD：该模式下会把新的语音任务放到语音任务之后，
                等前面的语音任务执行完了才会执行新的语音任务
             */
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
 
    public void release() {
        if (mTextToSpeech != null) {
            mTextToSpeech.shutdown();
        }
    }
 
    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS && mTextToSpeech != null) {

//            mTextToSpeech.setPitch(1.0f);
            //设置语速
            mTextToSpeech.setSpeechRate(3.0f);
            int result = mTextToSpeech.setLanguage(Locale.CHINESE);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                    || result == TextToSpeech.ERROR) {
                Log.d("ylt111", "onInit 数据丢失或语言不支持");
            }
            if (result == TextToSpeech.LANG_AVAILABLE) {
                Log.d("ylt111", "onInit 支持该语言");
            }
            Log.d("ylt111", "onInit ok");
        }
    }
 
    @Override
    public void onStart(String utteranceId) {
        Log.d("ylt111", "onStart " + utteranceId);
    }
 
    @Override
    public void onDone(String utteranceId) {
        Log.d("ylt111", "onDone " + utteranceId);
    }
 
    @Override
    public void onError(String utteranceId) {
        Log.d("ylt111", "onError " + utteranceId);
    }
}