package news.agoda.com.technewssample.newsdetails;



import news.agoda.com.technewssample.News;


class NewsDetailsPresenterImpl implements NewsDetailsPresenter
{
    private NewsDetailsView view;
    private NewsDetailsInteractor newsDetailsInteractor;

    NewsDetailsPresenterImpl(NewsDetailsInteractor newsDetailsInteractor)
    {
        this.newsDetailsInteractor = newsDetailsInteractor;
    }

    @Override
    public void setView(NewsDetailsView view)
    {
        this.view = view;
    }

    @Override
    public void destroy()
    {
        view = null;
    }

    @Override
    public void showDetails(News news)
    {
        if (isViewAttached())
        {
            view.showDetails(news);
        }
    }

    private boolean isViewAttached()
    {
        return view != null;
    }


}
