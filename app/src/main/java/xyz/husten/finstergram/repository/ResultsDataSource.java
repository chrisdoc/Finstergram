package xyz.husten.finstergram.repository;

import java.util.List;
import xyz.husten.finstergram.model.Result;
import xyz.husten.finstergram.model.SearchResult;

public interface ResultsDataSource {
  interface LoadResultsCallback {

    void onResultsLoaded(SearchResult result);

    void onDataNotAvailable(String error);
  }

  interface GetResultCallback {

    void onResultLoaded(Result result);

    void onResultNotAvailable(String error);
  }

  public void loadResults(double latitude, double longitude, int distance, LoadResultsCallback callback);
  public void loadResult(String id, GetResultCallback callback);
  public void refreshResults();
}
