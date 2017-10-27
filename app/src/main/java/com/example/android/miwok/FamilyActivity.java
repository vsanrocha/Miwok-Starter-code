package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity
        extends AppCompatActivity {

    private MediaPlayer mPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = (new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.raw.family_father, "father", "әpә", R.drawable.family_father));
        words.add(new Word(R.raw.family_mother, "mother", "әṭa", R.drawable.family_mother));
        words.add(new Word(R.raw.family_son, "son", "angsi", R.drawable.family_son));
        words.add(new Word(R.raw.family_daughter, "daughter", "tune", R.drawable.family_daughter));
        words.add(new Word(R.raw.family_older_brother, "older brother", "taachi", R.drawable.family_older_brother));
        words.add(new Word(R.raw.family_younger_brother, "younger brother", "chalitti", R.drawable.family_younger_brother));
        words.add(new Word(R.raw.family_older_sister, "older sister", "teṭe", R.drawable.family_older_sister));
        words.add(new Word(R.raw.family_younger_sister, "younger sister", "kolliti", R.drawable.family_younger_sister));
        words.add(new Word(R.raw.family_grandmother, "grandmother", "ama", R.drawable.family_grandmother));
        words.add(new Word(R.raw.family_grandfather, "grandfather", "paapa", R.drawable.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.word_item);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                Word word = words.get(position);

                mPlayer = MediaPlayer.create(FamilyActivity.this, word.getMiwokAudio());

                mPlayer.start();

                mPlayer.setOnCompletionListener(mCompletionListener);
            }


        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();

    }

    public void releaseMediaPlayer(){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }
}
