package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.annotations.SerializedName;

public class ResponseMangasData {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("attributes")
    private ResponseMangasAttributes attributes;

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

    public ResponseMangasAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ResponseMangasAttributes attributes) {
        this.attributes = attributes;
    }
}
