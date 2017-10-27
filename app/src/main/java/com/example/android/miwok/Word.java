package com.example.android.miwok;

/**
 * Created by Vinicius on 25/08/2017.
 */

public class Word {

    private String mMiwokTranslation;

    private String mDefaultTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mMiwokAudio;

    public Word(int miwokAudio, String defaultTranslation, String miwokTranslation, int imageResourceId){
        mMiwokAudio = miwokAudio;
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;

    }

    public Word(int miwokAudio, String defaultTranslation, String miwokTranslation){
        mMiwokAudio = miwokAudio;
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;

    }

    public String getMiwokLanguage(){
        return mMiwokTranslation;
    }

    public String getDefaultLanguague(){
        return mDefaultTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getMiwokAudio(){
        return mMiwokAudio;
    }


}
