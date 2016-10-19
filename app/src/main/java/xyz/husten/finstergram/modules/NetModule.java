package xyz.husten.finstergram.modules;

import android.app.Application;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import xyz.husten.finstergram.api.InstagramApi;
import xyz.husten.finstergram.api.InstagramAuthInterceptor;

@Module
public class NetModule {
  private final String instagramBaseUrl;
  private final String instagramAuthToken;

  public NetModule(String instagramBaseUrl, String instagramAuthToken) {
    this.instagramBaseUrl = instagramBaseUrl;
    this.instagramAuthToken = instagramAuthToken;
  }

  @Provides
  @Singleton
  Cache provideOkHttpCache(Application application) {
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    Cache cache = new Cache(application.getCacheDir(), cacheSize);
    return cache;
  }

  @Provides
  @Singleton
  Moshi provideMoshi() {
    Moshi.Builder builder = new Moshi.Builder();
    return builder.build();
  }

  @Provides
  @Singleton
  InstagramAuthInterceptor provideInstagramAuthInterceptor() {
    return new InstagramAuthInterceptor(instagramAuthToken);
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(Cache cache, InstagramAuthInterceptor authInterceptor) {
    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .cache(cache)
        .build();
    return client;
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(Moshi moshi, OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(instagramBaseUrl)
        .client(okHttpClient)
        .build();
    return retrofit;
  }

  @Provides
  @Singleton
  InstagramApi provideInstagramApi(Retrofit retrofit) {
    return retrofit.create(InstagramApi.class);
  }

}
