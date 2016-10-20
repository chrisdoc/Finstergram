package xyz.husten.finstergram.helpers;

import java.io.IOException;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.husten.finstergram.model.SearchResult;

public class FailureCall implements Call<SearchResult> {
  private Throwable failure;

  public FailureCall(Throwable response) {
    this.failure = response;
  }

  @Override public Response<SearchResult> execute() throws IOException {
    return null;
  }

  @Override public void enqueue(Callback<SearchResult> callback) {
    callback.onFailure(this, failure);
  }

  @Override public boolean isExecuted() {
    return false;
  }

  @Override public void cancel() {

  }

  @Override public boolean isCanceled() {
    return false;
  }

  @Override public Call<SearchResult> clone() {
    return null;
  }

  @Override public Request request() {
    return null;
  }
}
