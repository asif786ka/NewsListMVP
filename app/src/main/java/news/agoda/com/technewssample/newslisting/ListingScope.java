package news.agoda.com.technewssample.newslisting;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ListingScope
{
}
