package chatsid.webservice;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChatSIDWSRxInterface {

    @GET
    Single<RestrictionResponse> getRestrictionResponse(
            @Url String fullUrl
    );

}
