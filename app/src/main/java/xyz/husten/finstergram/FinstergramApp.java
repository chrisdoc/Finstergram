package xyz.husten.finstergram;

import android.app.Application;
import xyz.husten.finstergram.components.DaggerNetComponent;
import xyz.husten.finstergram.components.NetComponent;
import xyz.husten.finstergram.modules.AppModule;
import xyz.husten.finstergram.modules.NetModule;

public class FinstergramApp extends Application {
  private NetComponent netComponent;

  @Override public void onCreate() {
    super.onCreate();

    netComponent = DaggerNetComponent.builder()
        .appModule(new AppModule(getApplicationContext()))
        .netModule(new NetModule("https://api.instagram.com/v1/", BuildConfig.INSTAGRAM_ACCESS_TOKEN))
        .build();
  }

  public NetComponent getNetComponent() {
    return netComponent;
  }
}
