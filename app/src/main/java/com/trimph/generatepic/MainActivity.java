package com.trimph.generatepic;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.trimph.generatepic.utils.Article;
import com.trimph.generatepic.utils.WebViewHelper;

public class MainActivity extends AppCompatActivity {

    String url;
    public long mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "http://www.jianshu.com/p/ed2852eff92e";
        final WebView webView = (WebView) findViewById(R.id.webview);
        final TextView tv = (TextView) findViewById(R.id.Tv);


        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mTime = SystemClock.uptimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(SystemClock.uptimeMillis() - mTime < 300){
//                            tv.setVisibility(View.GONE);
                        }
                        break;
                }
                return false;
            }
        });

        WebViewHelper.getInstence().initWebView(webView, new WebViewHelper.GetDateListener() {
            @Override
            public void getDateListener(String text) {
                Log.e("Trimph", text);
                Intent intent=new Intent(MainActivity.this,ShareActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable("Article",new Article(text,
                        TextUtils.isEmpty(WebViewHelper.getInstence().getTitle())?"":WebViewHelper.getInstence().getTitle()));
                intent.putExtra("ABundle",bundle);
                startActivity(intent);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Trimph", "setOnClickListener");
                /**
                 * 先執行點擊事件 然後通過 js函數獲取頁面上選中的内容
                 */
                WebViewHelper.getInstence().setUpWebView(webView);
            }
        });

        webView.loadUrl(url);


    }
}
