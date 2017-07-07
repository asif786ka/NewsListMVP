package news.agoda.com.technewssample.newslisting;



import dagger.Module;
import dagger.Provides;
import news.agoda.com.technewssample.network.RequestHandler;


@Module
public class ListingModule
{
    @Provides
    NewsListingInteractor provideMovieListingInteractor(RequestHandler requestHandler)
    {
        return new NewsListingInteractorImpl(requestHandler);
    }

    @Provides
    NewsListingPresenter provideMovieListingPresenter(NewsListingInteractor interactor)
    {
        return new NewsListingPresenterImpl(interactor);
    }
}
