package com.trident.neomusic;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Outline;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lhjlb on 03.06.2017.
 */

public class FloatingMusicControl extends RelativeLayout {

    private ObjectAnimator animation;
    private boolean isPlaying=false;
    private ImageView playBackground;
    private ImageView playImage;
    private View v;

    public FloatingMusicControl(Context context) {
        super(context);
        init();
    }

    public FloatingMusicControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatingMusicControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        v = inflate(getContext(), R.layout.floating_music_control, this);

        v.findViewById(R.id.frameLayout).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(findViewById(R.id.context_menu).getVisibility() != View.GONE) {
                    findViewById(R.id.context_menu).setVisibility(View.GONE);
                    findViewById(R.id.triangle).setVisibility(View.GONE);
                }else{
                    findViewById(R.id.context_menu).setVisibility(View.VISIBLE);
                    findViewById(R.id.triangle).setVisibility(View.VISIBLE);
                }
                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(100);
                return true;
            }
        });
        v.findViewById(R.id.frameLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClick();
            }
        });
        v.findViewById(R.id.frameLayout).setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeLeft() {
                Toast.makeText(getContext(), "Следующая песня", Toast.LENGTH_SHORT).show();
            }

        });

        ((ImageView) v.findViewById(R.id.context_menu_item2).findViewById(R.id.item_icon))
                .setImageResource(R.drawable.ic_repeat_black_18dp);

        ((TextView) v.findViewById(R.id.context_menu_item2).findViewById(R.id.item_caption))
                .setText("Повтор");

        ((ImageView) v.findViewById(R.id.context_menu_item1).findViewById(R.id.item_icon))
                .setImageResource(R.drawable.ic_skip_previous_black_18dp);

        ((TextView) v.findViewById(R.id.context_menu_item1).findViewById(R.id.item_caption))
                .setText("Предыдущий трек");

        ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 100); // see this max value coming back here, we animale towards that value
        animation.setDuration (200000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());

        playBackground = (ImageView) findViewById(R.id.play_background);
        playImage = (ImageView) findViewById(R.id.play_image);
    }

    public void OnClick(){

        //TODO: Animation API19

        playBackground.setAlpha((float)0.5);
        playImage.setAlpha((float)1.0);
        if(isPlaying){
            ((ImageView) v.findViewById(R.id.play_image)).setImageResource(R.drawable.ic_play_arrow_white_24dp);
            isPlaying = !isPlaying;
            animation.pause();
            playBackground.animate().alpha(0)
                    .setDuration(500)
                    .setStartDelay(3000);
            playImage.animate().alpha(0)
                    .setDuration(500)
                    .setStartDelay(3000);
        }else{
            ((ImageView) v.findViewById(R.id.play_image)).setImageResource(R.drawable.ic_pause_white_24dp);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /// ..
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setOutlineProvider(new CustomOutline(w, h));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private class CustomOutline extends ViewOutlineProvider {

        int width;
        int height;

        CustomOutline(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setRect(0, 0, width, height);
        }
    }

}


