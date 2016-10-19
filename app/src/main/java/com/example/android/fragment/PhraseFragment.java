package com.example.android.fragment;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.miwok.R;
import com.example.android.miwok.word;
import com.example.android.miwok.wordAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhraseFragment extends Fragment {
    private MediaPlayer mediaplayer;
    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaplayer.pause();
                mediaplayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaplayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };


    public PhraseFragment() {
        // Required empty public constructor
    }

    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        mAudioManager =(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        //add phrases
        final ArrayList<word> words=new ArrayList<word>();

        words.add(new word("where are you going?","minto wuksus",R.raw.phrase_are_you_coming));
        words.add(new word("What is your name?","tinnə oyaase'nə",R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...","oyaaset... ",R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?","michəksəs?",R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good. ","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?","əənəs'aa?",R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.","həə’ əənəm",R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming","əənəm",R.raw.phrase_im_coming));
        words.add(new word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new word("Come here.","ənni'nem",R.raw.phrase_come_here));

        wordAdapter wordadapter = new wordAdapter(getActivity(), words,R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(wordadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word=words.get(position);
                releaseMediaPlayer();
                //when user click the other item ,we should cancel the previous one first.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaplayer= MediaPlayer.create(getActivity(),word.getmAudioResourceId());
                    mediaplayer.start();
                    //when this mean not the only one context,we should use the exactly activity.
                    mediaplayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaplayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaplayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaplayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
