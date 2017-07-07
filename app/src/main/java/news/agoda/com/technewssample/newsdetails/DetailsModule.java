package news.agoda.com.technewssample.newsdetails;



import dagger.Module;
import dagger.Provides;
import news.agoda.com.technewssample.network.RequestHandler;


@Module
public class DetailsModule
{
    @Provides
    @DetailsScope
    NewsDetailsInteractor provideInteractor(RequestHandler requestHandler)
    {
        return new NewsDetailsInteractorImpl(requestHandler);
    }

    @Provides
    @DetailsScope
    NewsDetailsPresenter providePresenter(NewsDetailsInteractor detailsInteractor)
    {
        return new NewsDetailsPresenterImpl(detailsInteractor);
    }
}
