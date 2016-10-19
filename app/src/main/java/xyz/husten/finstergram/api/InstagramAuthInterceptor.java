package xyz.husten.finstergram.api;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class InstagramAuthInterceptor implements Interceptor {
  
  final String accessToken;

  public InstagramAuthInterceptor(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    HttpUrl originalHttpUrl = originalRequest.url();

    HttpUrl url = originalHttpUrl.newBuilder()
        .addQueryParameter("access_token", accessToken)
        .build();

    // Create new request builder
    Request.Builder requestBuilder = originalRequest.newBuilder()
        .url(url);

    Request request = requestBuilder.build();
    return chain.proceed(request);
  }
}
