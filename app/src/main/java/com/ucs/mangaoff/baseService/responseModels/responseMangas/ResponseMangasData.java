package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMangasData {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("attributes")
    private ResponseMangasAttributes attributes;

    @SerializedName("relationships")
    private List<ResponseMangasRelationship> relationships;

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

    public List<ResponseMangasRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<ResponseMangasRelationship> relationships) {
        this.relationships = relationships;
    }
}
