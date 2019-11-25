package com.a.tmdbclient.ui.peoples.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.a.tmdbclient.R;
import com.a.tmdbclient.api.NetworkUtils;
import com.a.tmdbclient.api.peoples.PeopleDetails;
import com.a.tmdbclient.ui.peoples.PeoplesPresenter;
import com.bumptech.glide.Glide;

public class PeopleDetailsActivity extends AppCompatActivity {

    private TextView title;
    private TextView tag;
    private TextView description;
    private ImageView poster;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);
        init();

        int id = getIntent().getIntExtra("id", 0);
        PeoplesPresenter.getPeopleDetails(id, this);
    }

    private void init() {
        title = findViewById(R.id.people_details_title);
        tag = findViewById(R.id.people_details_tag);
        description = findViewById(R.id.people_details_description);
        poster = findViewById(R.id.people_details_poster);
        progressBar = findViewById(R.id.people_details_progress_bar);
    }

    public void setDetails(PeopleDetails details) {
        progressBar.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText(details.getName());
        tag.setVisibility(View.VISIBLE);
        tag.setText(details.getPlaceOfBirth());
        description.setVisibility(View.VISIBLE);
        description.setText(details.getBiography());
        poster.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(NetworkUtils.IMG_BASE_URL.concat(details.getProfilePath()))
                .into(poster);
    }

}