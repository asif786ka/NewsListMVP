package news.agoda.com.technewssample.newsdetails;


import news.agoda.com.technewssample.News;

public interface NewsDetailsPresenter
{
    void showDetails(News news);

    void setView(NewsDetailsView view);

    void destroy();
}
