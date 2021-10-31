package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.annotations.SerializedName;

public class ResponseMangasRelationship {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("attributes")
    private ResponseMangasRelAttributes attributes;

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

    public ResponseMangasRelAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ResponseMangasRelAttributes attributes) {
        this.attributes = attributes;
    }
}
