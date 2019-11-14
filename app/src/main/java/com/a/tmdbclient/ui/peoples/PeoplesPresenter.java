package com.a.tmdbclient.ui.peoples;

import android.content.Context;

import com.a.tmdbclient.App;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeoplesRepository;
import com.a.tmdbclient.api.peoples.pojo.PeopleDetails;
import com.a.tmdbclient.api.peoples.pojo.PeopleModel;
import com.a.tmdbclient.ui.peoples.view.PeopleDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class PeoplesPresenter {

    @Inject
    PeoplesRepository repository;
    private PeoplesView mView;

    private PeopleRecyclerViewAdapter mAdapter;
    private String searchQuery;
    private int searchPage = 1;

    public PeoplesPresenter(){
        App.getAppComponent().inject(this);
    }

    public void setView(PeoplesView view){
        mView = view;
    }

    public void setAdapter(PeopleRecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    public void getPeopleDetails(int id,final PeopleDetailsActivity activity) {
        repository.getPeopleDetails(id, new NetworkUtils.PeopleDetailsLoadCallback() {
            @Override
            public void onLoadFail(Call call) {
            }

            @Override
            public void onLoadSuccess(Response response, PeopleDetails peopleDetails) {
                activity.setDetails(peopleDetails);
            }
        });
    }

    public void getPopularPeoples(int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.getPopularPeoples(page, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                    mAdapter.addData(peopleModels);
                    mView.setProgressBarVisibility(false);
                }
            });
        }
    }

    public void searchPeoples(String query,int page, Context context) {
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            mView.setSearchProgressBarVisibility(true);
            searchQuery = query;
            searchPage = page;
            repository.searchPeoples(query,page, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.setSearchProgressBarVisibility(false);
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> movieModels) {
                    mView.setSearchProgressBarVisibility(false);
                    mAdapter.setSearchData(movieModels);
                }
            });
        }
    }

    public void searchMorePeoples(Context context){
        if (NetworkUtils.isInternetUnavailable(context)) {
            mView.showNoInternetError();
        } else {
            repository.searchPeoples(searchQuery, ++searchPage, new NetworkUtils.PeopleLoadCallback() {
                @Override
                public void onLoadFail(Call call) {
                    mView.showApiError();
                }

                @Override
                public void onLoadSuccess(Response response, List<PeopleModel> peopleModels) {
                    mAdapter.addSearchData(peopleModels);
                }
            });
        }
    }

}
