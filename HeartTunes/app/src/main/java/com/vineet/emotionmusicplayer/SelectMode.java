package com.vineet.emotionmusicplayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.microsoft.projectoxford.emotion.contract.RecognizeResult;

public class SelectMode extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
    }

    public void activityRecognize(View v) {
        Intent intent = new Intent(this, RecognizeActivity.class);
        startActivity(intent);
    }

    public void selectedEmotionActivityNavigation(int emotionIndex) {
        Intent intent = new Intent(this, RecognitionResultActivity.class);
        intent.putExtra("emotionIndex",emotionIndex);
        Log.d(String.valueOf(emotionIndex), "selectedEmotionActivityNavigation: ");
        startActivity(intent);
    }

    public void activityRecognizeAnger(View v) {
        selectedEmotionActivityNavigation(0);
    }
    public void activityRecognizeContempt(View v) {
        selectedEmotionActivityNavigation(1);
    }
    public void activityRecognizeDisgust(View v) {
        selectedEmotionActivityNavigation(2);
    }
    public void activityRecognizeFear(View v) {
        selectedEmotionActivityNavigation(3);
    }
    public void activityRecognizeHappiness(View v) {
        selectedEmotionActivityNavigation(4);
    }
    public void activityRecognizeNeutral(View v) {
        selectedEmotionActivityNavigation(5);
    }
    public void activityRecognizeSadness(View v) {
        selectedEmotionActivityNavigation(6);
    }
    public void activityRecognizeSurprise(View v) {
        selectedEmotionActivityNavigation(7);
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
