package com.trimph.generatepic;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;

import com.trimph.generatepic.utils.Article;
import com.trimph.generatepic.utils.GeneratePictureView;

import java.io.FileNotFoundException;

/**
 * Created by tao on 2016/10/9.
 */

public class ShareActivity extends Activity {
    public GeneratePictureView generatePictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);

        generatePictureView = (GeneratePictureView) findViewById(R.id.generatePic);

        Bundle bundle = getIntent().getBundleExtra("ABundle");

        if (bundle == null) {
            return;
        }

        Article article = bundle.getParcelable("Article");

        if (article == null) {
            return;
        }

        generatePictureView.init(article.content, article.title, "Trimph", "2016.10.9");


        /**
         * 放到圖庫中
         *
         */
        try {
            MediaStore.Images.Media.insertImage(ShareActivity.this.getContentResolver(),"圖片路徑","圖片name","description");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
