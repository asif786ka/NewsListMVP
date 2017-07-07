package news.agoda.com.technewssample.newslisting;



import dagger.Subcomponent;

@ListingScope
@Subcomponent(modules = {ListingModule.class})
public interface NewsListingComponent
{
    NewsListingFragment inject(NewsListingFragment fragment);

}
