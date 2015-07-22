package net.resand.thefm.io;

import net.resand.thefm.domain.Artist;
import net.resand.thefm.io.model.HypedArtistResponse;
import net.resand.thefm.io.model.TopArtistsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**  * Created by resand */
public interface LastFmApiService {

    @GET(ApiConstants.URL_HYPED_ARTISTS)
    void getHypedArtists(@Query(ApiConstants.PARAM_API_KEY) String key,Callback<HypedArtistResponse> serverResponse);

    @GET(ApiConstants.URL_TOP_ARTIST)
    void getTopArtists(@Query(ApiConstants.PARAM_API_KEY) String key,Callback<TopArtistsResponse> serverRespones);

    @GET(ApiConstants.URL_ARTIST_INFO)
    void getArtistInfo(@Query(ApiConstants.PARAM_API_KEY) String key, @Query(ApiConstants.PARAM_ARTIST) String artistName, Callback<Artist> serverResponse);

    @GET(ApiConstants.URL_HYPED_ARTISTS)
    Observable<HypedArtistResponse> getHypedArtists(@Query(ApiConstants.PARAM_API_KEY) String key);

    @GET(ApiConstants.URL_ARTIST_INFO)
    Observable<Artist> getArtistInfo(@Query(ApiConstants.PARAM_API_KEY) String key,@Query(ApiConstants.PARAM_ARTIST) String name);

    @GET(ApiConstants.URL_TOP_ARTIST)
    Observable<TopArtistsResponse> getTopArtists(@Query(ApiConstants.PARAM_API_KEY) String key);
}
