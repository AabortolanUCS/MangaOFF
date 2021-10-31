package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.annotations.SerializedName;
import com.ucs.mangaoff.baseService.responseModels.baseResponse.BaseResponse;

import java.util.List;

public class ResponseMangas extends BaseResponse {

    @SerializedName("data")
    private List<ResponseMangasData> data;

    public List<ResponseMangasData> getData() {
        return data;
    }

    public void setData(List<ResponseMangasData> data) {
        this.data = data;
    }
}
