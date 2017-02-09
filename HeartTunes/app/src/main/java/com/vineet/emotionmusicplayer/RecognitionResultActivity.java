package com.vineet.emotionmusicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecognitionResultActivity extends ActionBarActivity {
    int emotionIndex;
    private ArrayList<Songs> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition_result);
        Intent intent = getIntent();
        emotionIndex = intent.getIntExtra("emotionIndex",-1);
        if(emotionIndex==0){
            findViewById(R.id.emotionAnger).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionAngerText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==1){
            findViewById(R.id.emotionContempt).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionContemptText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==2) {
            findViewById(R.id.emotionDisgust).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionDisgustText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==3){
            findViewById(R.id.emotionFear).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionFearText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==4){
            findViewById(R.id.emotionHappy).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionHappyText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==5){
            findViewById(R.id.emotionNeutral).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionNeutralText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==6){
            findViewById(R.id.emotionSad).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionSadText).setVisibility(View.VISIBLE);
        } else if(emotionIndex==7){
            findViewById(R.id.emotionSurprise).setVisibility(View.VISIBLE);
            findViewById(R.id.emotionSurpriseText).setVisibility(View.VISIBLE);
        }
    }

    public void recheckRecognition(View v) {
        Intent intent = new Intent(this, SelectMode.class);
        startActivity(intent);
    }

    public void looksGood(View v) {

        //get array list from shared preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("songList", null);
        Type type = new TypeToken<ArrayList<Songs>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        //Generate song emotion list
        ArrayList<Songs> songEmotionList = new ArrayList<Songs>();
        String title, path;
        long songId;
        int eIndex;

        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i).getEmotionIndex()==emotionIndex){
                title = arrayList.get(i).getSongTitle();
                path = arrayList.get(i).getSongPath();
                songId = arrayList.get(i).getSongID();
                eIndex = arrayList.get(i).getEmotionIndex();
                songEmotionList.add(new Songs(songId,title,path,eIndex));
            }
        }

        //store song emotion list into shared preferences
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        gson = new Gson();
        json = gson.toJson(songEmotionList);
        editor.putString("songEmotionList", json);
        editor.commit();

        Intent intent = new Intent(this, MusicPlayer.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AppInfo.class);
            this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
