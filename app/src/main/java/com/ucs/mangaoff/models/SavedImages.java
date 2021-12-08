package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

import java.io.Serializable;

public class SavedImages extends SugarRecord implements Serializable {
    private byte[] image;
    private long chapterId;
    private int page;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Bitmap getImageBitmap() {
        return Utils.getImage(image);
    }

    public void setImageBitmap(Bitmap image) {
        this.image = Utils.getBytes(image);
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
