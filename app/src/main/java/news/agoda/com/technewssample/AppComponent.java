package news.agoda.com.technewssample;



import javax.inject.Singleton;

import dagger.Component;
import news.agoda.com.technewssample.newsdetails.DetailsComponent;
import news.agoda.com.technewssample.newsdetails.DetailsModule;
import news.agoda.com.technewssample.newslisting.NewsListingComponent;
import news.agoda.com.technewssample.newslisting.ListingModule;
import news.agoda.com.technewssample.network.NetworkModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class})
public interface AppComponent
{
    DetailsComponent plus(DetailsModule detailsModule);

    NewsListingComponent plus(ListingModule listingModule);
}
