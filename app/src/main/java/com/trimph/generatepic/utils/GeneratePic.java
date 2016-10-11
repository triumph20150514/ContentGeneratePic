package com.trimph.generatepic.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.trimph.generatepic.R;

/**
 * Created by tao on 2016/10/9.
 */

public class GeneratePic extends FrameLayout {
    public GeneratePic(Context context) {
        super(context);
    }

    public GeneratePic(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GeneratePic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view=LayoutInflater.from(context).inflate(R.layout.generate_pic_layout,null,false);
        WebView webView= (WebView) view.findViewById(R.id.smart_webView);

        webView.loadUrl("file://android-assets/markdown.html");

    }


}
