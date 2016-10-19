package com.example.android.miwok;

/**
 * Created by Administrator on 9/24/2016.
 */
public class word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mAudioResourceId;
    private int mImageResourceId = NO_IMAGE_PROVIDED;  //default the Imageview has not image resource.
    private static final int NO_IMAGE_PROVIDED = -1;

    public word(String DefaultTranslation,String miwokTranslation,int audioResourceId){
       mDefaultTranslation =DefaultTranslation;
        mMiwokTranslation =miwokTranslation;
        mAudioResourceId=audioResourceId;
    }
    public word(String DefaultTranslation,String miwokTranslation,int imageResourceId,int audioResourceId){
        mDefaultTranslation =DefaultTranslation;
        mMiwokTranslation =miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId=audioResourceId;
    }

    public String getmDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int getImageResourceId() {return mImageResourceId;}
    public boolean hasImage(){ return mImageResourceId != NO_IMAGE_PROVIDED;} //the value is true or false.
    public int getmAudioResourceId(){return mAudioResourceId;}
    }
