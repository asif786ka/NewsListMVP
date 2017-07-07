package news.agoda.com.technewssample.newslisting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import news.agoda.com.technewssample.Constants;
import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.R;
import news.agoda.com.technewssample.newsdetails.NewsDetailsActivity;
import news.agoda.com.technewssample.newsdetails.NewsDetailsFragment;


public class NewsListingActivity extends AppCompatActivity implements NewsListingFragment.Callback
{
    public static final String DETAILS_FRAGMENT = "DetailsFragment";
    private boolean twoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        if (findViewById(R.id.news_details_container) != null)
        {
            twoPaneMode = true;

            if (savedInstanceState == null)
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.news_details_container, new NewsDetailsFragment())
                        .commit();
            }
        } else
        {
            twoPaneMode = false;
        }
    }

    private void setToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.news_sample);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onNewsLoaded(News news)
    {
        if(twoPaneMode)
        {
            loadNewsFragment(news);
        } else
        {
            // Do not load in single pane view
        }
    }

    @Override
    public void onNewsClicked(News news)
    {
        if (twoPaneMode)
        {
            loadNewsFragment(news);
        } else
        {
            startMovieActivity(news);
        }
    }

    private void startMovieActivity(News news)
    {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.NEWS, news);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void loadNewsFragment(News news)
    {
        NewsDetailsFragment newsDetailsFragment = NewsDetailsFragment.getInstance(news);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.news_details_container, newsDetailsFragment, DETAILS_FRAGMENT)
                .commit();
    }
}
