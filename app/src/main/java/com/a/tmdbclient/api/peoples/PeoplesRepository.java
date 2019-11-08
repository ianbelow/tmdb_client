package com.a.tmdbclient.api.peoples;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.a.tmdbclient.App;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.pojo.PeopleDetails;
import com.a.tmdbclient.api.peoples.pojo.PeoplePageModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PeoplesRepository {

    @Inject
    Retrofit mRetrofit;

    @Inject
    public PeoplesRepository() {
        App.getAppComponent().inject(this);
    }

    private PeoplesApiService getAPI() {
        return mRetrofit.create(PeoplesApiService.class);
    }

    public AsyncTask<Void, Void, Void> getPopularPeoples(int page, NetworkUtils.PeopleLoadCallback callback) {
        return new PeoplesRepository.PopularPeoplesTask(page, callback).execute();
    }

    private class PopularPeoplesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.PeopleLoadCallback mCallback;

        PopularPeoplesTask(int page, NetworkUtils.PeopleLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPopularPeoples(mPage, NetworkUtils.API_KEY).enqueue(new Callback<PeoplePageModel>() {
                @Override
                public void onResponse(@NonNull Call<PeoplePageModel> call, @NonNull Response<PeoplePageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getResults());
                }

                @Override
                public void onFailure(@NonNull Call<PeoplePageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public AsyncTask<Void, Void, Void> getPeopleDetails(int page, NetworkUtils.PeopleDetailsLoadCallback callback) {
        return new PeopleDetailsTask(page, callback).execute();
    }

    private class PeopleDetailsTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.PeopleDetailsLoadCallback mCallback;

        PeopleDetailsTask(int page, NetworkUtils.PeopleDetailsLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPeopleDetails(mPage, NetworkUtils.API_KEY).enqueue(new Callback<PeopleDetails>() {
                @Override
                public void onResponse(@NonNull Call<PeopleDetails> call, @NonNull Response<PeopleDetails> response) {
                    mCallback.onLoadSuccess(response, response.body());
                }

                @Override
                public void onFailure(@NonNull Call<PeopleDetails> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

}