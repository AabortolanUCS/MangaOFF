package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.annotations.SerializedName;

public class ResponseMangasRelAttributes {

    @SerializedName("description")
    private String description;

    @SerializedName("fileName")
    private String fileName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
