package com.a.tmdbclient.api.shows;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowPageModel {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<ShowModel> showModels = null;

    public ShowPageModel() {
    }

    public ShowPageModel(Integer page, List<ShowModel> showModels) {
        super();
        this.page = page;
        this.showModels = showModels;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<ShowModel> getShowModels() {
        return showModels;
    }

    public void setShowModels(List<ShowModel> showModels) {
        this.showModels = showModels;
    }

}