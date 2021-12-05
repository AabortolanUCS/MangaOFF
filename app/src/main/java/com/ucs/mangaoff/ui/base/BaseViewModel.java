package com.ucs.mangaoff.ui.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BaseViewModel implements Serializable, Parcelable {

    public BaseViewModel() {

    }

    // Parceable methods

    protected BaseViewModel(Parcel in) {
    }

    public static final Creator<BaseViewModel> CREATOR = new Creator<BaseViewModel>() {
        @Override
        public BaseViewModel createFromParcel(Parcel in) {
            return new BaseViewModel(in);
        }

        @Override
        public BaseViewModel[] newArray(int size) {
            return new BaseViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
