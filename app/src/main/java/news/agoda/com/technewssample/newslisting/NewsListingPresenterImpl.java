package news.agoda.com.technewssample.newslisting;


import java.util.List;

import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.util.RxUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsListingPresenterImpl implements NewsListingPresenter
{
    private NewsListingView view;
    private NewsListingInteractor moviesInteractor;
    private Subscription fetchSubscription;

    public NewsListingPresenterImpl(NewsListingInteractor interactor)
    {
        moviesInteractor = interactor;
    }

    @Override
    public void setView(NewsListingView view)
    {
        this.view = view;
        displayMovies();
    }

    @Override
    public void destroy()
    {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    @Override
    public void displayMovies()
    {
        showLoading();
        fetchSubscription = moviesInteractor.fetchNews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieFetchSuccess, this::onMovieFetchFailed);
    }

    private void showLoading()
    {
        if (isViewAttached())
        {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<News> movies)
    {
        if (isViewAttached())
        {
            view.showNews(movies);
        }
    }

    private void onMovieFetchFailed(Throwable e)
    {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached()
    {
        return view != null;
    }
}
