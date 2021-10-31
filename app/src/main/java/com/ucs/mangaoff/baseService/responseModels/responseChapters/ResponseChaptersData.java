package com.ucs.mangaoff.baseService.responseModels.responseChapters;

import com.google.gson.annotations.SerializedName;

public class ResponseChaptersData {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("attributes")
    private ResponseChaptersAttributes attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ResponseChaptersAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ResponseChaptersAttributes attributes) {
        this.attributes = attributes;
    }
}
