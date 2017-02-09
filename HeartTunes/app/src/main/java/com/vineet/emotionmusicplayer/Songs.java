package com.vineet.emotionmusicplayer;

/**
 * Created by Student on 15-01-2017.
 */
public class Songs {
    private long mSongID;
    private String mSongTitle;
    private String mSongPath;
    private int mEmotionIndex;

    public Songs(long id, String title, String path, int emotionIndex){
        mSongID = id;
        mSongTitle = title;
        mSongPath = path;
        mEmotionIndex = emotionIndex;
    }

    public long getSongID(){
        return mSongID;
    }

    public String getSongTitle(){
    return mSongTitle;
    }

    public String getSongPath(){
        return mSongPath;
    }

    public int getEmotionIndex(){
        return mEmotionIndex;
    }
}
