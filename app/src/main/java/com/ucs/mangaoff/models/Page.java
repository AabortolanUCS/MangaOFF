package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Page implements Serializable {

    private byte[] photo;
    private String hashPage;

    public Page(Bitmap photo) {
       setPhotoBitmap(photo);
    }

    public Page() { }

    public byte[] getPhoto() {
        return photo;
    }

    public Bitmap getPhotoBitmap() {
        if(photo != null){
            return Utils.getImage(photo);
        }
        return null;
    }

    public void setPhotoBitmap(Bitmap photoBitmap) {
        photo = Utils.getBytes(photoBitmap);
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getHashPage() {
        return hashPage;
    }

    public void setHashPage(String hashPage) {
        this.hashPage = hashPage;
    }
}
