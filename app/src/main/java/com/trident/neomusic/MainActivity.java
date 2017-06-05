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
        setContentView(R.layout.floating_music_control);

        findViewById(R.id.main_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTasks();
            }
        });

        findViewById(R.id.frameLayout).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(findViewById(R.id.context_menu).getVisibility() != View.GONE) {
                    findViewById(R.id.context_menu).setVisibility(View.GONE);
                    findViewById(R.id.triangle).setVisibility(View.GONE);
                }else{
                    findViewById(R.id.context_menu).setVisibility(View.VISIBLE);
                    findViewById(R.id.triangle).setVisibility(View.VISIBLE);
                }
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(100);
                return true;
            }
        });
        findViewById(R.id.frameLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClick();
            }
        });
        findViewById(R.id.frameLayout).setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                Toast.makeText(getBaseContext(), "Следующая песня", Toast.LENGTH_SHORT).show();
            }

        });

        ((ImageView) findViewById(R.id.context_menu_item2).findViewById(R.id.item_icon))
                .setImageResource(R.drawable.ic_repeat_black_18dp);

        ((TextView) findViewById(R.id.context_menu_item2).findViewById(R.id.item_caption))
                .setText("Повтор");

        ((ImageView) findViewById(R.id.context_menu_item1).findViewById(R.id.item_icon))
                .setImageResource(R.drawable.ic_skip_previous_black_18dp);

        ((TextView) findViewById(R.id.context_menu_item1).findViewById(R.id.item_caption))
                .setText("Предыдущий трек");

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 100); // see this max value coming back here, we animale towards that value
        animation.setDuration (200000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());

        playBackground = (ImageView) findViewById(R.id.play_background);
        playImage = (ImageView) findViewById(R.id.play_image);
    }

    public void OnClick(){
        playBackground.setAlpha((float)0.5);
        playImage.setAlpha((float)1.0);
        if(isPlaying){
            ((ImageView) findViewById(R.id.play_image)).setImageResource(R.drawable.ic_play_arrow_white_24dp);
            isPlaying = !isPlaying;
            animation.pause();
            playBackground.animate().alpha(0)
                    .setDuration(500)
                    .setStartDelay(3000);
            playImage.animate().alpha(0)
                    .setDuration(500)
                    .setStartDelay(3000);
        }else{
            ((ImageView) findViewById(R.id.play_image)).setImageResource(R.drawable.ic_pause_white_24dp);
            isPlaying = !isPlaying;
            if(animation.getCurrentPlayTime() == 0){
                animation.start();
            }else{
                animation.resume();
            }
            playBackground.animate().alpha(0)
                    .setDuration(500)
                    .setStartDelay(3000);
            playImage.animate().alpha(0)
                    .setDuration(500)
                    .setStartDelay(3000);
        }
    }

    public void cancelTasks(){
        findViewById(R.id.context_menu).setVisibility(View.GONE);
        findViewById(R.id.triangle).setVisibility(View.GONE);
    }
}
