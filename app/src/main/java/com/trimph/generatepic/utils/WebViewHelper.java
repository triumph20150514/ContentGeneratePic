package com.trimph.generatepic.utils;

import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by tao on 2016/10/9.
 */

public class WebViewHelper {


    /**
     * 初始化webView 設置
     * @param webView
     */
    public void initWebView(WebView webView){
        WebSettings webSettings=webView.getSettings();




    }






    /**
     * 内部類
     * des: 調用此方法需要實現接口内方法
     */
    static class WebAppJsInterface {

        public GetDateListener getDateListener;

        public WebAppJsInterface(GetDateListener getDateListener) {
            this.getDateListener = getDateListener;
        }

        @JavascriptInterface
        public void getText(String text) {
            getDateListener.getDateListener(text);
        }

    }

    /**
     * 接口
     */
    interface GetDateListener {
        void getDateListener(String text);
    }


}
