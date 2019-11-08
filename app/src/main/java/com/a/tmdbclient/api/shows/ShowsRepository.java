package com.a.tmdbclient.api.shows;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.a.tmdbclient.App;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.shows.pojo.ShowDetails;
import com.a.tmdbclient.api.shows.pojo.ShowPageModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowsRepository {

    @Inject
    Retrofit mRetrofit;

    public ShowsRepository() {
        App.getAppComponent().inject(this);
    }

    private ShowApiService getAPI() {
        return mRetrofit.create(ShowApiService.class);
    }

    public AsyncTask<Void, Void, Void> getPopularShows(int page, NetworkUtils.ShowLoadCallback callback) {
        return new ShowsRepository.PopularShowTask(page, callback).execute();
    }

    private class PopularShowTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        PopularShowTask(int page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getPopularShows(mPage, NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(@NonNull Call<ShowPageModel> call, @NonNull Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getShowModels());
                }

                @Override
                public void onFailure(@NonNull Call<ShowPageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public AsyncTask<Void, Void, Void> getTopRatedShows(int page, NetworkUtils.ShowLoadCallback callback) {
        return new ShowsRepository.TopRatedShowTask(page, callback).execute();
    }

    private class TopRatedShowTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        TopRatedShowTask(int page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getTopRatedShows(mPage, NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(@NonNull Call<ShowPageModel> call, @NonNull Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getShowModels());
                }

                @Override
                public void onFailure(@NonNull Call<ShowPageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public AsyncTask<Void, Void, Void> getUpcomingShows(int page, NetworkUtils.ShowLoadCallback callback) {
        return new ShowsRepository.UpcomingShowTask(page, callback).execute();
    }

    private class UpcomingShowTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        UpcomingShowTask(int page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getUpcomingShows(mPage, NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(@NonNull Call<ShowPageModel> call, @NonNull Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getShowModels());
                }

                @Override
                public void onFailure(@NonNull Call<ShowPageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public AsyncTask<Void, Void, Void> getNowPlayingShows(int page, NetworkUtils.ShowLoadCallback callback) {
        return new ShowsRepository.NowPlayingShowTask(page, callback).execute();
    }

    private class NowPlayingShowTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.ShowLoadCallback mCallback;

        NowPlayingShowTask(int page, NetworkUtils.ShowLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getNowPlayingShows(mPage, NetworkUtils.API_KEY).enqueue(new Callback<ShowPageModel>() {
                @Override
                public void onResponse(@NonNull Call<ShowPageModel> call, @NonNull Response<ShowPageModel> response) {
                    mCallback.onLoadSuccess(response, response.body().getShowModels());
                }

                @Override
                public void onFailure(@NonNull Call<ShowPageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

    public AsyncTask<Void, Void, Void> getShowDetails(int page, NetworkUtils.ShowDetailsLoadCallback callback) {
        return new ShowsRepository.ShowDetailsTask(page, callback).execute();
    }

    private class ShowDetailsTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkUtils.ShowDetailsLoadCallback mCallback;

        ShowDetailsTask(int page, NetworkUtils.ShowDetailsLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getAPI().getShowDetails(mPage, NetworkUtils.API_KEY).enqueue(new Callback<ShowDetails>() {
                @Override
                public void onResponse(@NonNull Call<ShowDetails> call, @NonNull Response<ShowDetails> response) {
                    mCallback.onLoadSuccess(response, response.body());
                }

                @Override
                public void onFailure(@NonNull Call<ShowDetails> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }

}