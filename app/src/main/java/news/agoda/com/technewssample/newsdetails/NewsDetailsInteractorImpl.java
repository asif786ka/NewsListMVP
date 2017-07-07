package news.agoda.com.technewssample.newsdetails;


import news.agoda.com.technewssample.network.RequestHandler;


class NewsDetailsInteractorImpl implements NewsDetailsInteractor
{
    private RequestHandler requestHandler;

    NewsDetailsInteractorImpl(RequestHandler requestHandler)
    {
        this.requestHandler = requestHandler;
    }

}
