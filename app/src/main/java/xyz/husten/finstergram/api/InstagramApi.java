package xyz.husten.finstergram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.husten.finstergram.model.SearchResult;

public interface InstagramApi {
  @GET("media/search")
  public Call<SearchResult> search(@Query("lat") String latitude, @Query("lng") String longitude);
}
