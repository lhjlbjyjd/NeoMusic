package com.trident.neomusic;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator animation;
    private boolean isPlaying=false;
    private ImageView playBackground;
    private ImageView playImage;


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
    }

    public void cancelTasks(){
        findViewById(R.id.context_menu).setVisibility(View.GONE);
        findViewById(R.id.triangle).setVisibility(View.GONE);
    }
}
