package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MusicPlayer mMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.raw.color_red,"red", "weṭeṭṭi", R.drawable.color_red));
        words.add(new Word(R.raw.color_green,"green", "chokokki", R.drawable.color_green));
        words.add(new Word(R.raw.color_brown,"brown", "ṭakaakki", R.drawable.color_brown));
        words.add(new Word(R.raw.color_gray,"gray", "ṭopoppi", R.drawable.color_gray));
        words.add(new Word(R.raw.color_black,"black", "kululli", R.drawable.color_black));
        words.add(new Word(R.raw.color_white,"white", "kelelli", R.drawable.color_white));
        words.add(new Word(R.raw.color_dusty_yellow,"dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow));
        words.add(new Word(R.raw.color_mustard_yellow,"mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.word_item);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                Word word = words.get(position);

                if (mMusicPlayer == null) {
                    mMusicPlayer = new MusicPlayer(ColorsActivity.this, word.getMiwokAudio());
                }

                mMusicPlayer.play();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    public void releaseMediaPlayer() {
        if (mMusicPlayer != null) {
            mMusicPlayer.releaseMediaPlayer();
            mMusicPlayer = null;
        }
    }
}