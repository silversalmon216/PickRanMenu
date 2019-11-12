package com.factory.salmon.pickranmenu;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TitleTextView extends AppCompatTextView {

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/NotoSansCJKkr-Black.otf"));
    }
}
