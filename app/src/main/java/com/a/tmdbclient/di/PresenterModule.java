package com.a.tmdbclient.di;

import com.a.tmdbclient.ui.movies.MoviesPresenter;
import com.a.tmdbclient.ui.peoples.PeoplesPresenter;
import com.a.tmdbclient.ui.shows.ShowsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class PresenterModule {

    @Provides
    @Singleton
    MoviesPresenter provideMoviesPresenter(){
        return new MoviesPresenter();
    }

    @Provides
    @Singleton
    ShowsPresenter provideShowsPresenter(){
        return new ShowsPresenter();
    }

    @Provides
    @Singleton
    PeoplesPresenter providePeoplesPresenter(){
        return new PeoplesPresenter();
    }

}