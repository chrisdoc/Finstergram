package xyz.husten.finstergram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.husten.finstergram.model.SearchResult;
import xyz.husten.finstergram.model.SingleResult;

public interface InstagramApi {
  @GET("media/search")
  public Call<SearchResult> search(@Query("lat") double latitude, @Query("lng") double longitude, @Query("distance") int distance);
  @GET("media/{media-id}")
  public Call<SingleResult> search(@Path("media-id") String id);
}
