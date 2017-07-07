package news.agoda.com.technewssample.newsdetails;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import news.agoda.com.technewssample.BaseApplication;
import news.agoda.com.technewssample.Constants;
import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.R;

public class NewsDetailsFragment extends Fragment implements NewsDetailsView, View.OnClickListener
{
    @Inject
    NewsDetailsPresenter newsDetailsPresenter;

    @Bind(R.id.news_item_poster)
    ImageView poster;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.news_name)
    TextView title;
    @Bind(R.id.news_year)
    TextView releaseDate;
    @Bind(R.id.news_rating)
    TextView rating;
    @Bind(R.id.news_description)
    TextView newsoverview;
    @Bind(R.id.trailers_label)
    TextView label;
    @Bind(R.id.trailers)
    LinearLayout trailers;
    @Bind(R.id.trailers_container)
    HorizontalScrollView horizontalScrollView;
    @Bind(R.id.reviews_label)
    TextView reviews;
    @Bind(R.id.reviews)
    LinearLayout reviewsContainer;
    @Bind(R.id.favorite)
    FloatingActionButton favorite;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private News news;

    public NewsDetailsFragment()
    {
        // Required empty public constructor
    }

    public static NewsDetailsFragment getInstance(@NonNull News news)
    {
        Bundle args = new Bundle();
        args.putParcelable(Constants.NEWS, news);
        NewsDetailsFragment newsDetailsFragment = new NewsDetailsFragment();
        newsDetailsFragment.setArguments(args);
        return newsDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createDetailsComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_news_details, container, false);
        ButterKnife.bind(this, rootView);
        setToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null)
        {
            News news = (News) getArguments().get(Constants.NEWS);
            if (news != null)
            {
                this.news = news;
                newsDetailsPresenter.setView(this);
                newsDetailsPresenter.showDetails((news));
            }
        }
    }

    private void setToolbar()
    {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(getString(R.string.news_details));
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else
        {
            // Don't inflate. Tablet is in landscape mode.
        }
    }

    @Override
    public void showDetails(News news)
    {
        Glide.with(getContext()).load(news.getNewsDetailPath()).into(poster);
        title.setText(news.getTitle());
        releaseDate.setText(String.format(getString(R.string.release_date), news.getReleaseDate()));
        rating.setText(String.format(getString(R.string.rating), String.valueOf(news.getVoteAverage())));
        newsoverview.setText(news.getOverview());
    }


    @OnClick(R.id.favorite)
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.favorite:
                goToURL(news.getId());
                break;

            default:
                break;
        }
    }

    private void onReviewClick(TextView view)
    {
        if (view.getMaxLines() == 5)
        {
            view.setMaxLines(500);
        } else
        {
            view.setMaxLines(5);
        }
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        newsDetailsPresenter.destroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseDetailsComponent();
    }

    void goToURL(String url){
        Uri uri = Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
