package xyz.husten.finstergram.modules;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

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