package news.agoda.com.technewssample;

import android.app.Application;
import android.os.StrictMode;

import news.agoda.com.technewssample.newsdetails.DetailsComponent;
import news.agoda.com.technewssample.newsdetails.DetailsModule;
import news.agoda.com.technewssample.newslisting.NewsListingComponent;
import news.agoda.com.technewssample.newslisting.ListingModule;
import news.agoda.com.technewssample.network.NetworkModule;


public class BaseApplication extends Application
{
    private AppComponent appComponent;
    private DetailsComponent detailsComponent;
    private NewsListingComponent newsListingComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public DetailsComponent createDetailsComponent()
    {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent()
    {
        detailsComponent = null;
    }

    public NewsListingComponent createListingComponent()
    {
        newsListingComponent = appComponent.plus(new ListingModule());
        return newsListingComponent;
    }

    public void releaseListingComponent()
    {
        newsListingComponent = null;
    }

    public NewsListingComponent getNewsListingComponent()
    {
        return newsListingComponent;
    }
}
