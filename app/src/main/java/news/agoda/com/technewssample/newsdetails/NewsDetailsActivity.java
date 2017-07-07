package news.agoda.com.technewssample.newsdetails;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import news.agoda.com.technewssample.Constants;
import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.R;


public class NewsDetailsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.NEWS))
            {
                News news = extras.getParcelable(Constants.NEWS);
                if (news != null)
                {
                    NewsDetailsFragment newsDetailsFragment = NewsDetailsFragment.getInstance(news);
                    getSupportFragmentManager().beginTransaction().add(R.id.news_details_container, newsDetailsFragment).commit();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
