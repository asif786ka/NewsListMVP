package news.agoda.com.technewssample.newslisting;

public interface NewsListingPresenter
{
    void displayMovies();

    void setView(NewsListingView view);

    void destroy();
}
