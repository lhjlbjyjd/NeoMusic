package com.trident.neomusic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by lhjlb on 04.06.2017.
 */

public class ContextMenuItem extends RelativeLayout {
    public ContextMenuItem(Context context) {
        super(context);
        init();
    }

    public ContextMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ContextMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.menu_item, this);
    }
}
