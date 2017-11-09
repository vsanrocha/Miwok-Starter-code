package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment
        extends Fragment {

    private MusicPlayer mMusicPlayer;

    public FamilyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

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

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

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