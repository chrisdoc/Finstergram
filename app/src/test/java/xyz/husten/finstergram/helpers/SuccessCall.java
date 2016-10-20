package xyz.husten.finstergram.helpers;

import java.io.IOException;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuccessCall<T> implements Call<T> {
  private T response;

  public SuccessCall(T response) {
    this.response = response;
  }

  @Override public Response<T> execute() throws IOException {
    return null;
  }

  @Override public void enqueue(Callback<T> callback) {
    callback.onResponse(this, Response.success(response));
  }

  @Override public boolean isExecuted() {
    return false;
  }

  @Override public void cancel() {

  }

  @Override public boolean isCanceled() {
    return false;
  }

  @Override public Call<T> clone() {
    return null;
  }

  @Override public Request request() {
    return null;
  }
}
