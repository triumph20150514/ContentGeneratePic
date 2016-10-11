package com.trimph.generatepic.utils;

import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tao on 2016/10/9.
 */

public class WebViewHelper {

    public static WebViewHelper webViewHelper;

    public static WebViewHelper getInstence() {
        if (webViewHelper == null) {
            webViewHelper = new WebViewHelper();
        }
        return webViewHelper;
    }


    public void setUpWebView(WebView webView) {
        String js = "(function getSelectedText() {" +
                "var txt;" +
                "if (window.getSelection) {" +
                "var range=window.getSelection().getRangeAt(0);" +
                "var container = window.document.createElement('div');" +
                "container.appendChild(range.cloneContents());" +
                "txt = container.innerHTML;" +
                "} else if (window.document.getSelection) {" +
                "var range=window.getSelection().getRangeAt(0);" +
                "var container = window.document.createElement('div');" +
                "container.appendChild(range.cloneContents());" +
                "txt = container.innerHTML;" +
                "} else if (window.document.selection) {" +
                "txt = window.document.selection.createRange().htmlText;" +
                "}" +
                "JSInterface.getText(txt);" +
//                "alert(txt);" +

                "})()";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript("javascript:" + js, null);
        } else {
            webView.loadUrl("javascript:" + js);
        }
        webView.clearFocus();
    }


    /**
     * 初始化webView 設置
     *
     * @param webView
     */
    public void initWebView(WebView webView, GetDateListener getDateListener) {
        WebSettings mSettings = webView.getSettings();

        mSettings.setAppCacheEnabled(true); //开启缓存
        mSettings.setJavaScriptEnabled(true);//开启javascript
        mSettings.setDomStorageEnabled(true);//开启DOM
        mSettings.setDefaultTextEncodingName("utf-8");//设置字符编码
        //设置web页面
        mSettings.setUseWideViewPort(true);// 调整到适合webview大小
        mSettings.setLoadWithOverviewMode(true);// 调整到适合webview大小
        mSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级

        /**
         *
         */
        webView.addJavascriptInterface(new WebAppJsInterface(getDateListener), "JSInterface");

        webView.setWebViewClient(new WebViewClient() {

            /**
             * 此方法返回True可以使用webView進行跳轉
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Map<String, String> map = request.getRequestHeaders();
                Set<Map.Entry<String, String>> set = map.entrySet();
                Iterator<Map.Entry<String, String>> iterator = set.iterator();
                map.entrySet();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                }
//                for (Map.Entry<String, String> entry : iterator.next()) {
//                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                title = view.getTitle();
                Log.e("Trimph  title", title);
                super.onPageFinished(view, url);
            }

            /**
             * 獲取 選中的圖片路徑
             * @param view
             * @param url
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                if (url.toLowerCase().contains(".jpg")
                        || url.toLowerCase().contains(".png")
                        || url.toLowerCase().contains(".gif")) {
                    if (images == null)
                        images = new ArrayList<String>();
                    Log.e("Trimph", url);
                    images.add(url);
                }
                super.onLoadResource(view, url);
            }
        });


    }

    public List<String> images = new ArrayList<>();
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取图片路径
     *
     * @return
     */
    public List<String> getAllListPath() {
        return images;
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
    public interface GetDateListener {
        public void getDateListener(String text);
    }

}
