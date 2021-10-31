package com.ucs.mangaoff.baseService.responseModels.baseResponse;

import com.google.gson.annotations.SerializedName;

public class ResponseEnglish {

    @SerializedName("en")
    private String en;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
