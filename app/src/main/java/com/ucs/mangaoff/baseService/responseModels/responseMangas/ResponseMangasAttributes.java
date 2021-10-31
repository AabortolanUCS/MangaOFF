package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.annotations.SerializedName;
import com.ucs.mangaoff.baseService.responseModels.baseResponse.ResponseEnglish;

public class ResponseMangasAttributes {

    @SerializedName("title")
    private ResponseEnglish title;

    @SerializedName("description")
    private ResponseEnglish description;

    public ResponseEnglish getTitle() {
        return title;
    }

    public void setTitle(ResponseEnglish title) {
        this.title = title;
    }

    public ResponseEnglish getDescription() {
        return description;
    }

    public void setDescription(ResponseEnglish description) {
        this.description = description;
    }
}
