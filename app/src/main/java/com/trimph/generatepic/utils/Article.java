package com.trimph.generatepic.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tao on 2016/10/9.
 */

public class Article implements Parcelable {

    public String content;
    public String title;

    public Article(String content, String title) {
        this.content = content;
        this.title = title;
    }

    protected Article(Parcel in) {
        content = in.readString();
        title = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeString(title);
    }
}
