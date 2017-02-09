package com.vineet.emotionmusicplayer;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.*;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

import com.vineet.emotionmusicplayer.MusicDatabaseHelper;

public class MusicTag extends ActionBarActivity {

    private ArrayList<Songs> arrayTempList;
    private ArrayList<Songs> arrayList;
    private ArrayList<Songs> arrayModifiedList;
    int sampleRate = 44100;
    int audioBufferSize = 4096;
    int bufferOverlap = 0;
    AudioProcessor p;
    MusicDatabaseHelper musicDatabaseHelper;

    int totalSongCount =0;
    int scanPercentage = 0;
    ArrayList<String> songList;
    int eIndex;
    String tagEmotionIndex;
    ListView tagSongView;
    TextView textPercentage;

    String currentTitle,currentPath;
    long currentId;
    int currentEmotionIndex = -1;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_tag);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("songTempList", null);
        Type type = new TypeToken<ArrayList<Songs>>() {}.getType();
        arrayTempList = gson.fromJson(json, type);

        totalSongCount = arrayTempList.size();

        new AndroidFFMPEGLocator(context);
        new LongOperation().execute(arrayTempList);
    }

    public void activitySelectMode(View v) {
        Intent intent = new Intent(this, SelectMode.class);
        startActivity(intent);
    }

    private class LongOperation extends AsyncTask<ArrayList<Songs>, ArrayList<Songs>, ArrayList<Songs>> {
        @Override
        protected ArrayList<Songs> doInBackground(ArrayList<Songs>... params) {
                    arrayList = new ArrayList<Songs>();
                    musicDatabaseHelper =new MusicDatabaseHelper(context);
                    for (int i = 0; i < arrayTempList.size(); i++) {
                        currentTitle =(String) arrayTempList.get(i).getSongTitle();
                        currentId =(int) arrayTempList.get(i).getSongID();
                        currentPath =(String) arrayTempList.get(i).getSongPath();

                        final AudioDispatcher dispatcher = AudioDispatcherFactory.fromPipe(currentPath, sampleRate, audioBufferSize, bufferOverlap);
                        PitchDetectionHandler pdh = new PitchDetectionHandler() {
                            @Override
                            public void handlePitch(PitchDetectionResult result,AudioEvent e) {
                                double pitchInHz = result.getPitch();
                                float probability = result.getProbability();
                                double rms = e.getRMS() * 100;
                                 if(pitchInHz>0 && probability>0.5 && rms>0){
                                    if((currentEmotionIndex = musicDatabaseHelper.getEmotion(pitchInHz,rms,context)) !=-1){
                                        arrayList.add(new Songs(currentId, currentTitle, currentPath, currentEmotionIndex));
                                        publishProgress(arrayList);
                                        currentEmotionIndex = -1;
                                        dispatcher.stop();
                                        dispatcher.removeAudioProcessor(p);
                                    }
                                }
                            }
                        };
                        p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, audioBufferSize, pdh);
                        dispatcher.addAudioProcessor(p);
                        dispatcher.run();
                    }
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Songs> arrayList) {

            arrayModifiedList = new ArrayList<Songs>();
            String title,path;
            long songId;

            //Mapping SongEmotion to UserEmotion
            for (int i = 0; i < arrayList.size(); i++) {
                title = arrayList.get(i).getSongTitle();
                path = arrayList.get(i).getSongPath();
                songId = arrayList.get(i).getSongID();
                if (arrayList.get(i).getEmotionIndex() == 0) {
                    arrayModifiedList.add(new Songs(songId, title, path, 5));
                } else if (arrayList.get(i).getEmotionIndex() == 1) {
                    arrayModifiedList.add(new Songs(songId, title, path, 6));
                    arrayModifiedList.add(new Songs(songId, title, path, 2));
                } else if (arrayList.get(i).getEmotionIndex() == 2) {
                    arrayModifiedList.add(new Songs(songId, title, path, 4));
                    arrayModifiedList.add(new Songs(songId, title, path, 7));
                } else if (arrayList.get(i).getEmotionIndex() == 3) {
                    arrayModifiedList.add(new Songs(songId, title, path, 0));
                    arrayModifiedList.add(new Songs(songId, title, path, 1));
                } else if (arrayList.get(i).getEmotionIndex() == 4) {
                    arrayModifiedList.add(new Songs(songId, title, path, 3));
                }
            }

            // Add array list to shared preferences
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(arrayModifiedList);
            editor.putString("songList", json);
            editor.commit();
            findViewById(R.id.tagSubmitButton).setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(ArrayList<Songs>... values) {

            scanPercentage = (arrayList.size()*100)/arrayTempList.size();
            textPercentage =  (TextView) findViewById(R.id.percentage);
            textPercentage.setText(String.valueOf(scanPercentage) + "%");

            tagSongView =  (ListView) findViewById(R.id.tagSongView);

            // create playlist song list names
            songList = new ArrayList<String>();
            String songViewList;
            for (int i = 0; i < arrayList.size(); i++) {
                eIndex =arrayList.get(i).getEmotionIndex();

                if(eIndex == 0)
                    tagEmotionIndex = "Calm";
                else if(eIndex == 1)
                    tagEmotionIndex = "Despair";
                else if(eIndex == 2)
                    tagEmotionIndex = "Joyful";
                else if(eIndex == 3)
                    tagEmotionIndex = "Aggressive";
                else
                    tagEmotionIndex = "Anxiety";

                songViewList =(String) arrayList.get(i).getSongTitle() + " Tag: " + tagEmotionIndex;
                songList.add(songViewList);
            }

            //populate list on array adapter
            ArrayAdapter<String> songAdapter = new ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_list_item_1,
                    songList);

            if(arrayList.size()>0)
                  tagSongView.setAdapter(songAdapter);
        }
    }

}
