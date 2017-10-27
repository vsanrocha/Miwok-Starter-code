package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by Vinicius on 20/10/2017.
 */

public class MusicPlayer {

    private int mUri;
    private Context mContext;
    private boolean mAudioFocusGranted = false;
    private boolean mAudioIsPlaying = false;
    private MediaPlayer mPlayer;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    private MediaPlayer.OnCompletionListener mCompletionListener = (new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            stop();
        }
    });


    public MusicPlayer(Context context, int uri) {
        mContext = context;
        mUri = uri;

        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

            @Override
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                        play();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        stop();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        pause();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        pause();
                        break;

                }

            }
        };
    }

    public void play() {
        if (!mAudioIsPlaying) {
            requestAduioFocus();
            if (mAudioFocusGranted) {
                if (mPlayer == null) {
                    mPlayer = MediaPlayer.create(mContext, mUri);
                }
                mPlayer.start();
                mAudioIsPlaying = true;

            }
        }
    }

    public void pause() {
        if (mAudioFocusGranted && mAudioIsPlaying) {
            mPlayer.pause();
            mAudioIsPlaying = false;
        }
    }

    public void stop() {
        if (mAudioFocusGranted && mAudioIsPlaying) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            mAudioIsPlaying = false;
            abandonAudioFocus();
        }
    }

    public void releaseMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            abandonAudioFocus();
        }
    }

    private boolean requestAduioFocus() {
        if (!mAudioFocusGranted) {
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

            int result = am.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mAudioFocusGranted = true;
            }
        }
        return mAudioFocusGranted;
    }

    private void abandonAudioFocus() {
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

        int result = am.abandonAudioFocus(mOnAudioFocusChangeListener);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mAudioFocusGranted = false;
        }

    }
}
