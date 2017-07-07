package news.agoda.com.technewssample.listing;

import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.newslisting.NewsListingInteractor;

import news.agoda.com.technewssample.newslisting.NewsListingPresenterImpl;
import news.agoda.com.technewssample.newslisting.NewsListingView;
import news.agoda.com.technewssample.util.RxSchedulersOverrideRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class NewsListingPresenterImplTest
{
    @Mock
    private NewsListingInteractor interactor;
    @Mock
    private NewsListingView view;
    @Mock
    Throwable throwable;
    @Mock
    private List<News> aNews;

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    private NewsListingPresenterImpl presenter;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        presenter = new NewsListingPresenterImpl(interactor);
    }

    @After
    public void teardown()
    {
        presenter.destroy();
    }

    @Test
    public void shouldBeAbleToDisplayNews()
    {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<List<News>> testSubscriber = new TestSubscriber<>();
        Observable<List<News>> responseObservable = Observable.just(aNews).subscribeOn(testScheduler);
        responseObservable.subscribe(testSubscriber);
        when(interactor.fetchNews()).thenReturn(responseObservable);

        presenter.setView(view);
        testScheduler.triggerActions();

        testSubscriber.assertNoErrors();
        testSubscriber.onCompleted();
        verify(view).showNews(aNews);
    }
}