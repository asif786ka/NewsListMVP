package news.agoda.com.technewssample.listing;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import news.agoda.com.technewssample.News;
import news.agoda.com.technewssample.newslisting.NewsListingInteractor;
import news.agoda.com.technewssample.newslisting.NewsListingPresenterImpl;
import news.agoda.com.technewssample.newslisting.NewsListingView;
import news.agoda.com.technewssample.util.RxSchedulersOverrideRule;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(RobolectricTestRunner.class)
public class NewsModelTest
{

    @Mock
    private News aNews;

    private String TITLE = "AMAZON News Title";
    private String DESCRIPTION = "AMAZON News Description";

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void teardown()
    {
    }

    @Test
    public void testTitle()  {
        //  create mock

        // define return value for method getTitle()
        when(aNews.getTitle()).thenReturn(TITLE);

        // use mock in test....
        assertEquals(aNews.getTitle(), "AMAZON News Title");
    }

    @Test
    public void testDescription()  {
        //  create mock

        // define return value for method getOverview()
        when(aNews.getOverview()).thenReturn(DESCRIPTION);

        // use mock in test....
        assertEquals(aNews.getOverview(), "AMAZON News Description");
    }
}