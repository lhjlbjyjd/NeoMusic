package com.trident.neomusic;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by lhjlb on 03.06.2017.
 */

public class FloatingMusicControl extends RelativeLayout {

    public FloatingMusicControl(Context context) {
        super(context);
    }

    private void init() {
        inflate(getContext(), R.layout.floating_music_control, this);
    }
}
