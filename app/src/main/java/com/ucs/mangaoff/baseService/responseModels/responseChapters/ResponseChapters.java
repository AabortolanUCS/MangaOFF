package com.ucs.mangaoff.baseService.responseModels.responseChapters;

import com.google.gson.annotations.SerializedName;
import com.ucs.mangaoff.baseService.responseModels.baseResponse.BaseResponse;

import java.util.List;

public class ResponseChapters extends BaseResponse {

    @SerializedName("data")
    private List<ResponseChaptersData> data;

    public List<ResponseChaptersData> getData() {
        return data;
    }

    public void setData(List<ResponseChaptersData> data) {
        this.data = data;
    }
}
