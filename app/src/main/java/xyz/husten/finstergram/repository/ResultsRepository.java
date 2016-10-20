package xyz.husten.finstergram.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.husten.finstergram.api.InstagramApi;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;
import xyz.husten.finstergram.model.SingleResult;

public class ResultsRepository implements ResultsDataSource {

  private final InstagramApi api;

  Map<String, Result> cachedResults;
  boolean cacheIsDirty = false;

  @Inject
  public ResultsRepository(InstagramApi api) {
    this.api = api;
  }

  @Override public void loadResults(double latitude, double longitude, int distance, final LoadResultsCallback callback) {
    if (cachedResults != null && !cacheIsDirty) {
      callback.onResultsLoaded(new SearchResult(cachedResults.values()));
      return;
    }

    api.search(latitude, longitude, distance).enqueue(new Callback<SearchResult>() {
      @Override public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
        refreshCache(response.body().results);
        callback.onResultsLoaded(response.body());
      }

      @Override public void onFailure(Call<SearchResult> call, Throwable t) {
        callback.onDataNotAvailable(t.getMessage());
      }
    });
  }

  @Override public void loadResult(String id, final GetResultCallback callback) {
    final Result cachedResult = getResultWithId(id);
    if(cachedResult != null) {
      callback.onResultLoaded(cachedResult);
    }

    api.search(id).enqueue(new Callback<SingleResult>() {
      @Override public void onResponse(Call<SingleResult> call, Response<SingleResult> response) {
        refreshCache(response.body().result);
        callback.onResultLoaded(response.body().result);
      }

      @Override public void onFailure(Call<SingleResult> call, Throwable t) {
        callback.onResultNotAvailable(t.getMessage());
      }
    });
  }

  @Override public void refreshResults() {
    cacheIsDirty = true;
  }

  private void refreshCache(List<Result> results) {
    if (cachedResults == null) {
      cachedResults = new LinkedHashMap<>();
    }
    cachedResults.clear();
    for (Result result : results) {
      cachedResults.put(result.id, result);
    }
    cacheIsDirty = false;
  }

  private void refreshCache(Result result) {
    if (cachedResults == null) {
      cachedResults = new LinkedHashMap<>();
    }

    cachedResults.remove(result.id);
    cachedResults.put(result.id, result);
  }


  @Nullable
  private Result getResultWithId(@NonNull String id) {
    if (cachedResults == null || cachedResults.isEmpty()) {
      return null;
    } else {
      return cachedResults.get(id);
    }
  }
}
