package com.vineet.emotionmusicplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MusicList extends ActionBarActivity {

    private ListView lv;
    private ArrayList<String> songList;
    private ArrayList<Songs> songEmotionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        lv = (ListView) findViewById(R.id.lv);

        //get song emotion list
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("songEmotionList", null);
        Type type = new TypeToken<ArrayList<Songs>>() {}.getType();
        songEmotionList = gson.fromJson(json, type);

        // create playlist song list names
        songList = new ArrayList<String>();
        String songViewList;
        for (int i = 0; i < songEmotionList.size(); i++) {
            songViewList =(String) songEmotionList.get(i).getSongTitle();
            songList.add(songViewList);
        }

        //populate list on array adapter
        ArrayAdapter<String> songAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                songList);

        lv.setAdapter(songAdapter);

        //set on click listener on list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                for(int i=1;i<=songEmotionList.size();i++){
                    if((int)id==i){
                        Intent myIntent = new Intent(view.getContext(), MusicPlayer.class);
                        myIntent.putExtra("index",i);
                        startActivityForResult(myIntent, 0);
                        break;
                    }
                }
            }
        });
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