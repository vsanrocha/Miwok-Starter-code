package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragmnet extends Fragment {

    private MusicPlayer mMusicPlayer;

    public NumbersFragmnet() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.raw.number_one,"one", "lutti", R.drawable.number_one));
        words.add(new Word(R.raw.number_two,"two", "otiiko", R.drawable.number_two));
        words.add(new Word(R.raw.number_three,"three", "tolookosu", R.drawable.number_three));
        words.add(new Word(R.raw.number_four,"four", "oyyisa", R.drawable.number_four));
        words.add(new Word(R.raw.number_five,"five", "massokka", R.drawable.number_five));
        words.add(new Word(R.raw.number_six,"six", "temmokka", R.drawable.number_six));
        words.add(new Word(R.raw.number_seven,"seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word(R.raw.number_eight,"eight", "kawinta", R.drawable.number_eight));
        words.add(new Word(R.raw.number_nine,"nine", "wo’e", R.drawable.number_nine));
        words.add(new Word(R.raw.number_ten,"ten", "na’aacha", R.drawable.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

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

