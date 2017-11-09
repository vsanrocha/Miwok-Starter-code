package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    private MusicPlayer mMusicPlayer;

    public ColorsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.raw.color_red,"red", "weṭeṭṭi", R.drawable.color_red));
        words.add(new Word(R.raw.color_green,"green", "chokokki", R.drawable.color_green));
        words.add(new Word(R.raw.color_brown,"brown", "ṭakaakki", R.drawable.color_brown));
        words.add(new Word(R.raw.color_gray,"gray", "ṭopoppi", R.drawable.color_gray));
        words.add(new Word(R.raw.color_black,"black", "kululli", R.drawable.color_black));
        words.add(new Word(R.raw.color_white,"white", "kelelli", R.drawable.color_white));
        words.add(new Word(R.raw.color_dusty_yellow,"dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow));
        words.add(new Word(R.raw.color_mustard_yellow,"mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.word_item);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                Word word = words.get(position);

                if (mMusicPlayer == null) {
                    mMusicPlayer = new MusicPlayer(getActivity(), word.getMiwokAudio());
                }

                mMusicPlayer.play();
            }
        });

        return rootView;

    }

    @Override
    public void onStop() {
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