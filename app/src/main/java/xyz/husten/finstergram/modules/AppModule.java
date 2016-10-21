package xyz.husten.finstergram.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import xyz.husten.finstergram.BuildConfig;
import xyz.husten.finstergram.model.LatitudeLongitude;

@Module
public class AppModule {

  Context context;

  public AppModule(Context context) {
    this.context = context;
  }

  @Provides
  @Singleton
  Context providesApplication() {
    return context;
  }

}