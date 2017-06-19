package com.trident.neomusic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator animation;
    private boolean isPlaying=false;
    private ImageView playBackground;
    private ImageView playImage;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTasks();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.songList);

        FastScroller fastScroller=(FastScroller)findViewById(R.id.fastscroller);
        fastScroller.setRecyclerView(mRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Song> songs = new ArrayList<>();
        
        songs.add(new Song("A New Song"));
        songs.add(new Song("B New Song"));
        songs.add(new Song("C New Song"));
        songs.add(new Song("D New Song"));
        songs.add(new Song("E New Song"));
        songs.add(new Song("F New Song"));
        songs.add(new Song("G New Song"));
        songs.add(new Song("H New Song"));
        songs.add(new Song("I New Song"));
        songs.add(new Song("J New Song"));
        songs.add(new Song("K New Song"));
        songs.add(new Song("L New Song"));
        songs.add(new Song("M New Song"));
        songs.add(new Song("N New Song"));
        songs.add(new Song("O New Song"));
        songs.add(new Song("P New Song"));
        songs.add(new Song("Q New Song"));
        songs.add(new Song("R New Song"));
        songs.add(new Song("S New Song"));
        songs.add(new Song("T New Song"));
        songs.add(new Song("U New Song"));
        songs.add(new Song("V New Song"));
        songs.add(new Song("W New Song"));
        songs.add(new Song("X New Song"));
        songs.add(new Song("Y New Song"));
        songs.add(new Song("Z New Song"));

        // specify an adapter (see also next example)
        mAdapter = new SongsListAdapter(this, songs);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void cancelTasks(){
        findViewById(R.id.context_menu).setVisibility(View.GONE);
        findViewById(R.id.triangle).setVisibility(View.GONE);
    }
}
