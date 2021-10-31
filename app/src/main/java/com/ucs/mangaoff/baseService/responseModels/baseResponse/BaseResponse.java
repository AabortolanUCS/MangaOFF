package com.ucs.mangaoff.baseService.responseModels.baseResponse;

import com.google.gson.annotations.SerializedName;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;

import java.util.List;

public class BaseResponse {

    @SerializedName("result")
    private String result;

    @SerializedName("response")
    private String response;

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("total")
    private Integer total;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
